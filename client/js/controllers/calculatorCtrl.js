
(function () {
    'use strict';
    app.controller('CalcController', function ($scope, $uibModal, $log) {
        // данные по рискам
        $scope.risks = {
            selectedRowNo: 1,
            riskDataSet: [
                {
                    insRiskTypeId: "1",
                    forIndividualTypeId: "1",
                    riskTypeName: "Д",
                    forIndividualTypeName: "Застрахованное лицо",
                    riskAmount: "2000",
                    payAmount: "500",
                    term: "10",
                    payTerm: "10",
                    nettoTariff: "0.0001"
                },
                {
                    insRiskTypeId: "2",
                    forIndividualTypeId: "1",
                    riskTypeName: "C",
                    forIndividualTypeName: "Застрахованное лицо",
                    riskAmount: "2000",
                    payAmount: "500",
                    term: "10",
                    payTerm: "10",
                    nettoTariff: "0.0001"
                },
                {
                    insRiskTypeId: "3",
                    forIndividualTypeId: "1",
                    riskTypeName: "C(НС)",
                    forIndividualTypeName: "Застрахованное лицо",
                    riskAmount: "200",
                    payAmount: "1",
                    term: "10",
                    payTerm: "10",
                    nettoTariff: "0.000133"
                },
                {
                    insRiskTypeId: "4",
                    forIndividualTypeId: "1",
                    riskTypeName: "C(ДТП)",
                    forIndividualTypeName: "Застрахованное лицо",
                    riskAmount: "1000",
                    payAmount: "10",
                    term: "10",
                    payTerm: "10",
                    nettoTariff: "0.000221"
                }
            ]};
        $scope.setSelected = function (rowNo) {
            $scope.risks.selectedRowNo = rowNo;
        };
        // lookup списки
        // Страховые риски
        var insEventRiskList = [
            {riskTypeId: 1, forIndividualTypeId: 1, name: 'Д застрахованного лица', riskTypeName: 'Дожитие', forIndividualTypeName: 'Застрахованного лица'},
            {riskTypeId: 2, forIndividualTypeId: 1, name: 'С застрахованного лица', riskTypeName: 'Смерть', forIndividualTypeName: 'Застрахованного лица'},
            {riskTypeId: 3, forIndividualTypeId: 1, name: 'С(ДТП) застрахованного лица', riskTypeName: 'С(НС)', forIndividualTypeName: 'Застрахованного лица'},
            {riskTypeId: 4, forIndividualTypeId: 1, name: 'С(НС) застрахованного лица', riskTypeName: 'С(ДТП)', forIndividualTypeName: 'Застрахованного лица'},
            {riskTypeId: 7, forIndividualTypeId: 1, name: 'Инвалидность застрахованного лица', riskTypeName: 'Инвалидность', forIndividualTypeName: 'Застрахованного лица'}
        ];

        // Программы страхования
        $scope.insSchemeList = [
            {id: '1', name: '1 Лайф'},
            {id: '2', name: '2 Пенсия'},
            {id: '3', name: '3 Добродетель'}
        ];
        // Правила страхования
        $scope.insSchemeRuleList = [
            {id: '1', name: 'Правило 1'},
            {id: '2', name: 'Правило 2'},
        ];
        // Валюта
        $scope.currencyList = [
            {id: 'UAH', name: 'Гривна'},
            {id: 'USD', name: 'Доллар'},
            {id: 'EUR', name: 'Евро'}
        ];
        // Частота оплаты взносов
        $scope.frequencyList = [
            {id: 'Y', name: 'Ежегодно'},
            {id: 'M', name: 'Ежемесячно'},
            {id: 'H', name: 'Раз в пол года'},
            {id: 'Q', name: 'Ежеквартально'},
            {id: 'E', name: 'Единовременно'}
        ];
        // Регион
        $scope.regionList = [
            {id: '1', name: 'Украина'}
        ];
        // Пол
        $scope.genderList = [
            {id: 'M', name: 'Мужчина'},
            {id: 'F', name: 'Женщина'}
        ];

        // данные для расчета
        $scope.data = {
            insScheme: null,
            currency: $scope.currencyList [0],
            insSchemeRule: null,
            paymentFrequency: $scope.frequencyList[0],
            region: $scope.regionList[0],
            insuredGender: $scope.genderList[0].id,
            insuredBirthDate: null
        };
        // date picker popup
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
        function disabled (data) {
            var date = data.date,
                mode = data.mode;
            return mode === 'day' && (date.getDay() === 0 || date.getDay() === 6);
        }
        $scope.open1 = function() {
            $scope.dtCalendar.opened = true;
        };
        // modal dialog

        $scope.addInsRisk = function (size) {

            var modalInstance = $uibModal.open({
                animation: true,
                templateUrl: 'myModalContent.html',
                controller: 'ModalInstanceCtrl',
                size: size,
                resolve: {
                    items: function () {
                        return insEventRiskList;
                    }
                }
            });

            modalInstance.result.then(function (selectedItem) {
                $scope.selectedInsRiskItem = selectedItem;
                $scope.risks.riskDataSet.push({
                    insRiskTypeId: selectedItem.insRiskTypeId,
                    forIndividualTypeId: selectedItem.forIndividualTypeId,
                    riskTypeName: selectedItem.riskTypeName,
                    forIndividualTypeName: selectedItem.forIndividualTypeName,
                    riskAmount: "",
                    payAmount: "",
                    term: "",
                    payTerm: "",
                    nettoTariff: ""
                });
            }, function () {
                $log.info('Modal dismissed at: ' + new Date());
            });
        };



        /* *** Actions *** */

        // при изменении программы страхования, определяем программу страхования
        // todo: обавить фильтрацию списка
        $scope.InsSchemeChange = function () {
            console.log("selected scheme with id = " + $scope.data.insScheme.id);

            var ix = $scope.data.insScheme.id;

            if (ix > $scope.insSchemeRuleList.length) $scope.data.insSchemeRule = $scope.insSchemeRuleList[0];
            else $scope.data.insSchemeRule = $scope.insSchemeRuleList[ix - 1];
        };

    });

})();

