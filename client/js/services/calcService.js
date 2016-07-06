app.service('calcService', function () {

    var riskDataSet = {
        data: [
            {
                rowNo: 1,
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
                rowNo: 2,
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
                rowNo: 3,
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
                rowNo: 4,
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
        ]
    };
    riskDataSet.selectedRisk = riskDataSet.data[0];

    this.getRiskData = function (setResult) {
        setResult(riskDataSet.data);
    };
    this.setSelectedRisk = function (risk) {
        riskDataSet.selectedRisk = risk;
    };
    this.isRiskSelected = function (risk) {
        return riskDataSet.selectedRisk == risk;
    };

    this.getInsuranceEventRisks = function (succes) {
        succes(
            [
                {
                    riskTypeId: 1,
                    forIndividualTypeId: 1,
                    name: 'Д застрахованного лица',
                    riskTypeName: 'Дожитие',
                    forIndividualTypeName: 'Застрахованного лица'
                },
                {
                    riskTypeId: 2,
                    forIndividualTypeId: 1,
                    name: 'С застрахованного лица',
                    riskTypeName: 'Смерть',
                    forIndividualTypeName: 'Застрахованного лица'
                },
                {
                    riskTypeId: 3,
                    forIndividualTypeId: 1,
                    name: 'С(ДТП) застрахованного лица',
                    riskTypeName: 'С(НС)',
                    forIndividualTypeName: 'Застрахованного лица'
                },
                {
                    riskTypeId: 4,
                    forIndividualTypeId: 1,
                    name: 'С(НС) застрахованного лица',
                    riskTypeName: 'С(ДТП)',
                    forIndividualTypeName: 'Застрахованного лица'
                },
                {
                    riskTypeId: 7,
                    forIndividualTypeId: 1,
                    name: 'Инвалидность застрахованного лица',
                    riskTypeName: 'Инвалидность',
                    forIndividualTypeName: 'Застрахованного лица'
                }
            ]);
    };
    this.getInsuranceSchemes = function (succes) {
        succes(
            data = [
                {id: '1', name: '1 Лайф'},
                {id: '2', name: '2 Пенсия'},
                {id: '3', name: '3 Добродетель'}
            ]);
    };
    this.getInsuranceSchemeRules = function (success) {
        success (data = [
            {id: '1', name: 'Правило 1'},
            {id: '2', name: 'Правило 2'}
        ]);
    };
    this.getCurrencies = function (success) {
        success(data = [
            {id: 'UAH', name: 'Гривна'},
            {id: 'USD', name: 'Доллар'},
            {id: 'EUR', name: 'Евро'}
        ]);
    };
    this.getFrequencies = function (success) {
        success(data = [
            {id: 'Y', name: 'Ежегодно'},
            {id: 'M', name: 'Ежемесячно'},
            {id: 'H', name: 'Раз в пол года'},
            {id: 'Q', name: 'Ежеквартально'},
            {id: 'E', name: 'Единовременно'}
        ]);
    };
    this.getRegions = function (success) {
        success(data = [
            {id: '1', name: 'Украина'}
        ]);
    };
    this.getGenders = function (success) {
        success(data = [
            {id: 'M', name: 'Мужчина'},
            {id: 'F', name: 'Женщина'}
        ]);
    };
});