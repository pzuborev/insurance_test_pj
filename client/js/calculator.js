(function () {
    'use strict';
    var app = angular.module('Calculator', ['ngMaterial']);

    app.controller('CalcController', function ($scope) {
        // данные по рискам
        $scope.risks = {
            selectedRowNo: 1,
            riskDataSet: [
                {
                    rowNo: "1",
                    riskName: "Д",
                    forIndividualType: "Застрахованное лицо",
                    riskAmount: "2000",
                    payAmount: "500",
                    term: "10",
                    payTerm: "10",
                    nettoTariff: "0.0001"
                },
                {
                    rowNo: "2",
                    riskName: "C",
                    forIndividualType: "Застрахованное лицо",
                    riskAmount: "2000",
                    payAmount: "500",
                    term: "10",
                    payTerm: "10",
                    nettoTariff: "0.0001"
                },
                {
                    rowNo: "3",
                    riskName: "C(НС)",
                    forIndividualType: "Застрахованное лицо",
                    riskAmount: "200",
                    payAmount: "1",
                    term: "10",
                    payTerm: "10",
                    nettoTariff: "0.000133"
                },
                {
                    rowNo: "4", riskName: "C(ДТП)",
                    forIndividualType: "Застрахованное лицо",
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


        // при изменении программы страхования, определяем программу страхования
        // todo: обавить фильтрацию списка
        $scope.InsSchemeChange = function () {
            console.log("selected scheme with id = " + $scope.data.insScheme.id);

            var ix = $scope.data.insScheme.id;

            if (ix > $scope.insSchemeRuleList.length) $scope.data.insSchemeRule = $scope.insSchemeRuleList[0];
            else $scope.data.insSchemeRule = $scope.insSchemeRuleList[ix - 1];
        };

    });
    /* app.controller('InsSchemeController', InsSchemeController);
     function InsSchemeController($timeout, $q, $log) {
     var self = this;
     self.simulateQuery = true;
     self.isDisabled = false;
     // list of `state` value/display objects
     self.items = loadAll();
     self.querySearch = querySearch;
     self.selectedItemChange = selectedItemChange;
     self.searchTextChange = searchTextChange;
     //$log.info(loadAll());
     // ******************************
     // Internal methods
     // ******************************
     /!**
     * Search for states... use $timeout to simulate
     * remote dataservice call.
     *!/
     function querySearch(query) {
     var results = query ? self.items.filter(createFilterFor(query)) : self.items,
     deferred;
     if (self.simulateQuery) {
     deferred = $q.defer();
     $timeout(function () {
     deferred.resolve(results);
     }, Math.random() * 1000, false);
     return deferred.promise;
     } else {
     return results;
     }
     }

     function searchTextChange(text) {
     $log.info('Text changed to ' + text);
     }

     function selectedItemChange(item) {
     $log.info('Item changed to ' + JSON.stringify(item));
     }

     /!**
     * Build `states` list of key/value pairs
     *!/
     function loadAll() {
     var allItems = '7 - "Лайф", 8 - "Пенсия", 9 - "Благосостояние"';
     return allItems.split(/, +/g).map(function (item) {
     return {
     value: item.toLowerCase(),
     display: item
     };
     });
     }

     /!**
     * Create filter function for a query string
     *!/
     function createFilterFor(query) {
     var lowercaseQuery = angular.lowercase(query);
     return function filterFn(item) {
     return (item.value.indexOf(lowercaseQuery) >= 0); /// === 0 точное совпадение
     };
     }
     }*/
})();

