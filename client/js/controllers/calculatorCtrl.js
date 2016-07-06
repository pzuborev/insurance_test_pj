(function () {
    'use strict';
    app.controller('CalcController', function ($scope, $uibModal, $log, calcService) {

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

        /*** Lookup списки ***/

        // Страховые риски
        var insEventRisks = [];
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
        // filling the lookup-lists
        calcService.getInsuranceEventRisks(function (data) {
            insEventRisks = data;
        });
        calcService.getInsuranceSchemes(function (data) {
            $scope.insuranceSchemes = data;
        });
        calcService.getInsuranceSchemeRules (function (data) {
            $scope.insuranceSchemeRules = data;
        });
        calcService.getCurrencies (function (data) {
            $scope.currencies = data;
        });
        calcService.getFrequencies(function (data) {
            $scope.frequencies = data;
        });
        calcService.getRegions (function (data) {
            $scope.regions = data;
        });
        calcService.getGenders(function (data) {
            $scope.genders = data;
        });

        /*** Данные для расчета ***/
        $scope.calcData = {
            insuranceScheme: null,
            currency: $scope.currencies [0],
            insuranceSchemeRule: null,
            paymentFrequency: $scope.frequencies[0],
            region: $scope.regions[0],
            insuredGender: $scope.genders[0].id,
            insuredBirthDate: null
        };

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

            var modalInstance = $uibModal.open({
                animation: true,
                templateUrl: 'myModalContent.html',
                controller: 'ModalInstanceCtrl',
                size: size,
                resolve: {
                    items: function () {
                        return insEventRisks;
                    }
                }
            });

            modalInstance.result.then(function (selectedItem) {
                $scope.selectedInsRiskItem = selectedItem;

                var newItem =
                {
                    rowNo: $scope.riskData.size + 1,
                    riskTypeId: selectedItem.riskTypeId,
                    forIndividualTypeId: selectedItem.forIndividualTypeId,
                    riskTypeName: selectedItem.riskTypeName,
                    forIndividualTypeName: selectedItem.forIndividualTypeName
                    //riskAmount: "2000",
                    //payAmount: "500",
                    //term: "10",
                    //payTerm: "10",
                    //nettoTariff: "0.0001"
                };

                $scope.riskData.push(newItem);
            }, function () {
                $log.info('Modal dismissed at: ' + new Date());
            });
        };


        /*** Actions ***/

        // при изменении программы страхования, определяем программу страхования
        // todo: обавить фильтрацию списка
        $scope.InsSchemeChange = function () {
            var ix = $scope.calcData.insuranceScheme.id;
            if (ix > $scope.insuranceSchemeRules.length) $scope.calcData.insuranceSchemeRule = $scope.insuranceSchemeRules[0];
            else $scope.calcData.insuranceSchemeRule = $scope.insuranceSchemeRules[ix - 1];
        };

    });

})();

