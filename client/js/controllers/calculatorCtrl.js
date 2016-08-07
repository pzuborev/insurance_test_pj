(function () {
    'use strict';
    app.controller('CalcController', function ($scope, $rootScope, $uibModal, $log, $q, calcService) {
            $log.info("*** init CalcController");

            /*** Данные для расчета ***/

            $scope.calcData = {
                insuranceScheme: null,
                currency: null,
                insuranceSchemeRule: null,
                paymentFrequency: null,
                region: null,
                insuredGender: null,
                insuredBirthDate: null
            };

            $scope.setSelectedRisk = function (risk) {
                $scope.selectedRisk = risk;
                //$scope.selectedRisk.payCount = calcService.getPayCount($scope.selectedRisk.payTerm,
                //    $scope.calcData.paymentFrequency);
            };
            $scope.isRiskSelected = function (risk) {
                return ($scope.selectedRisk == risk);
            };
            /*** Данные таблицы (условия страхования в разрезе рисков) ***/
            calcService.getRiskData(function (resultData) {
                $scope.riskData = resultData;
                $scope.selectedRisk = $scope.riskData[0];
            });

            /*** Lookup списки ***/

                // Программы страхования
            $scope.insuranceSchemes = [];
            // Правила страхования
            $scope.insuranceSchemeRules = [];
            // Валюта
            $scope.currencies = [];
            // Частота оплаты взносов
            $scope.frequencies = [];
            // Регионы
            $scope.regions = [];
            // Пол
            $scope.genders = [];


            calcService.getInsuranceSchemes(function (data) {
                    console.log("** calcService.getInsuranceSchemes");
                    $scope.insuranceSchemes = data;
                }
            );
            calcService.getInsuranceSchemeRules(function (data) {
                $scope.insuranceSchemeRules = data;
            });
            calcService.getCurrencies(function (data) {
                $scope.currencies = data;
                if ($scope.calcData.currency === null && $scope.currencies.length > 0)
                    $scope.calcData.currency = $scope.currencies[0];
            });
            calcService.getFrequencies(function (data) {
                $scope.frequencies = data;
                if ($scope.calcData.paymentFrequency === null && $scope.frequencies.length > 0) {
                    $scope.calcData.paymentFrequency = $scope.frequencies[0];
                }

            });
            calcService.getRegions(function (data) {
                $scope.regions = data;
                if ($scope.calcData.region === null && $scope.regions.length > 0)
                    $scope.calcData.region = $scope.regions[0];
            });
            calcService.getGenders(function (data) {
                $scope.genders = data;
                if ($scope.calcData.insuredGender === null && $scope.genders.length > 0)
                    $scope.calcData.insuredGender = $scope.genders[0].code;
            });

            /*** Date picker popup ***/

            $scope.dtCalendar = {
                opened: false
            };
            $scope.dateOptions = {
                dateDisabled: disabled,
                formatYear: 'yy',
                maxDate: new Date(4000, 1, 1),
                minDate: new Date(1900, 1, 1),
                startingDay: 1
            };
            function disabled(data) {
                var date = data.date,
                    mode = data.mode;
                return mode === 'day' && (date.getDay() === 0 || date.getDay() === 6);
            }

            $scope.open1 = function () {
                $scope.dtCalendar.opened = true;
            };


            /*** Actions ***/
            $scope.deleteInsRisk = function () {
                var index = $scope.riskData.indexOf($scope.selectedRisk);
                if ($scope.riskData.length > 1) {
                    if (index == 0)
                        $scope.selectedRisk = $scope.riskData[index + 1];
                    else
                        $scope.selectedRisk = $scope.riskData[index - 1]

                } else
                    $scope.selectedRisk = null;

                $scope.riskData.splice(index, 1);
            };

            $scope.addInsRisk = function (size) {
                if ($scope.calcData.insuranceScheme == null) {
                    alert("Выберите программу страхования");
                    return;
                }
                calcService.getEventRisksForScheme(
                    $scope.calcData.insuranceScheme.id,
                    function (schemeRisks) {
                        //$scope.setEventRisks (schemeRisks);
                        // todo add progress bar
                        var modalInstance = $uibModal.open({
                            animation: true,
                            templateUrl: 'myModalContent.html',
                            controller: 'ModalInstanceCtrl',
                            size: size,
                            resolve: {
                                items: function () {
                                    return schemeRisks;
                                }
                            }
                        });

                        modalInstance.result.then(function (selectedItem) {
                            $scope.selectedInsRiskItem = selectedItem;

                            var newItem =
                            {
                                rowNo: $scope.riskData.length + 1,
                                riskTypeId: selectedItem.insurancerisktypeid,
                                forIndividualTypeId: selectedItem.forindividualtypeid,
                                riskTypeName: selectedItem.insurancerisktypecode,
                                forIndividualTypeName: selectedItem.forindividualtypename
                            };

                            $scope.riskData.push(newItem);
                        }, function () {
                            $log.info('Modal dismissed at: ' + new Date());
                        });

                    }
                );

            };
            // при изменении программы страхования, определяем программу страхования
            // todo: добавить фильтрацию списка
            $scope.InsSchemeChange = function () {
                var ix = $scope.calcData.insuranceScheme.id;
                if (ix > $scope.insuranceSchemeRules.length) $scope.calcData.insuranceSchemeRule = $scope.insuranceSchemeRules[0];
                else $scope.calcData.insuranceSchemeRule = $scope.insuranceSchemeRules[ix - 1];

            };

            /*** Watch ***/
            $scope.$watch('selectedRisk.payTerm', function (newValue, oldValue) {
                $log.debug("*** selectedRisk.payTerm changed");
                if ($scope.selectedRisk != null) {
                    $scope.selectedRisk.payCount = calcService.getPayCount($scope.selectedRisk.payTerm,
                        $scope.calcData.paymentFrequency);
                }

            });

            $scope.$watch('calcData.paymentFrequency', function (newValue, oldValue) {
                $log.debug("*** paymentFrequency changed");
                $scope.riskData.forEach(function (item, i, arr) {
                    item.payCount = calcService.getPayCount(item.payTerm, newValue);
                });
            });

        }
    )
    ;

})
();

