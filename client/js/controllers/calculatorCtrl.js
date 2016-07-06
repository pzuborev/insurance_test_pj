(function () {
    'use strict';
    app.controller('CalcController', function ($scope, $uibModal, $log, $q, calcService) {

        /*** Данные таблицы (условия страхования в разрезе рисков) ***/

        calcService.getRiskData(function (resultData) {
            $scope.riskData = resultData;
        });
        $scope.setSelectedRisk = function (risk) {
            calcService.setSelectedRisk(risk);
        };
        $scope.isRiskSelected = function (risk) {
            return calcService.isRiskSelected(risk);
        };

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

        /*** Lookup списки ***/
        // Страховые риски
        $scope.insEventRisks = [];
        $scope.setEventRisks = function (data) {
            $scope.insEventRisks = data;
            $log.log('****************'+ data.length);
            $log.log($scope.insEventRisks.length);
        };

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

        /***  Инициализация ***/

        calcService.getInsuranceSchemes(function (data) {
            $scope.insuranceSchemes = data;
        });
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
                //$log.log('************ set freq default ' + $scope.frequencies[0]);
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


        /*** Modal dialog ***/

        $scope.addInsRisk = function (size) {
            calcService.getEventRisksForScheme(
                $scope.calcData.insuranceScheme.id,
                function (schemeRisks){
                    //$scope.setEventRisks (schemeRisks);

                    var modalInstance = $uibModal.open({
                        animation: true,
                        templateUrl: 'myModalContent.html',
                        controller: 'ModalInstanceCtrl',
                        size: size,
                        resolve: {
                            items: function () {
                                return schemeRisks; // $scope.insEventRisks;
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


        /*** Actions ***/

            // при изменении программы страхования, определяем программу страхования
            // todo: добавить фильтрацию списка
        $scope.InsSchemeChange = function () {
            var ix = $scope.calcData.insuranceScheme.id;
            if (ix > $scope.insuranceSchemeRules.length) $scope.calcData.insuranceSchemeRule = $scope.insuranceSchemeRules[0];
            else $scope.calcData.insuranceSchemeRule = $scope.insuranceSchemeRules[ix - 1];

            //calcService.getEventRisksForScheme(
            //    $scope.calcData.insuranceScheme.id,
            //    this.setEventRisks
            //);
        };

    });

})();

