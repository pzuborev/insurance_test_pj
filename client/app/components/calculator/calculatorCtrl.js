(function () {
    'use strict';
    app.controller('CalcController', function ($scope, $rootScope, $uibModal, $log, $q, calcService, messageService) {
            $log.info("*** init CalcController");

            /*** Данные для расчета ***/

            $scope.calcData = {
                insuranceScheme: null,
                currency: null,
                insuranceSchemeRule: null,
                paymentFrequency: null,
                region: null,
                insuredGender: null,
                insuredBirthDate: null,
                arcDate: null
            };

            $scope.calcData.arcDate = new Date();

            //  for quick test
            $scope.calcData.insuredBirthDate = new Date();


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
                    $scope.calcData.insuranceScheme = $scope.insuranceSchemes[0];
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
                openedArcDate: false,
                openedInsuredBirthDate: false
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

            $scope.openInsuredBirthDate = function () {
                $scope.dtCalendar.openedInsuredBirthDate = true;
            };
            $scope.openArcDate = function () {
                $scope.dtCalendar.openedArcDate = true;
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
                            templateUrl: 'shared/modalForms/riskList/riskListForm.tpl.html',
                            controller: 'riskListModalFormCtrl',
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
                                riskType: {id: selectedItem.insurancerisktypeid, name: selectedItem.insurancerisktypename},
                                forIndividualType: {id: selectedItem.forindividualtypeid, name: selectedItem.forindividualtypename},
                                payTerm: null,
                                term: null,
                                riskAmount: null,
                                payAmount: null,
                                nettoTariff: null,
                                payCount: null
                            };

                            $scope.riskData.push(newItem);
                        }, function () {
                            $log.info('Modal dismissed at: ' + new Date());
                        });

                    }
                );

            };

            $scope.performCalc = function () {
               calcService.performCalc($scope.calcData, $scope.riskData,
                   function success (risks) {
                       $scope.riskData = risks;
                       $scope.selectedRisk = $scope.riskData && $scope.riskData[0];
                   },
                   function failure (error) {
                       messageService.show(error && error.message, messageService.ERROR);
                   }
               );
            };

            /*** Watch ***/

            $scope.$watchGroup(['calcData.insuranceScheme', 'insuranceSchemeRules'], function (newValue, oldValue) {
                if ($scope.calcData.insuranceScheme == null || $scope.insuranceSchemeRules.length == 0) return;
                $log.debug("*** calcData.insuranceScheme changed");
                var ix = $scope.calcData.insuranceScheme.id;
                if (ix > $scope.insuranceSchemeRules.length) $scope.calcData.insuranceSchemeRule = $scope.insuranceSchemeRules[0];
                else $scope.calcData.insuranceSchemeRule = $scope.insuranceSchemeRules[ix - 1];


            });

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

