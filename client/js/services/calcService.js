app.service('calcService', function ($http, $log) {

    var riskDataSet = {
        data: [
            {
                rowNo: 1,
                riskTypeId: "1",
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
                riskTypeId: "2",
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
                riskTypeId: "3",
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
                riskTypeId: "4",
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


    this.getEventRisksForScheme = function (schemeId, success) {
        /*success(
            [
                {
                    riskTypeId: 1,
                    forIndividualTypeId: 1,
                    name: 'Д застрахованного лица',
                    riskTypeName: 'Дожитие',
                    forIndividualTypeName: 'Застрахованное лица'
                },
                {
                    riskTypeId: 2,
                    forIndividualTypeId: 1,
                    name: 'С застрахованного лица',
                    riskTypeName: 'Смерть',
                    forIndividualTypeName: 'Застрахованное лица'
                },
                {
                    riskTypeId: 3,
                    forIndividualTypeId: 1,
                    name: 'С(ДТП) застрахованного лица',
                    riskTypeName: 'С(НС)',
                    forIndividualTypeName: 'Застрахованное лица'
                },
                {
                    riskTypeId: 4,
                    forIndividualTypeId: 1,
                    name: 'С(НС) застрахованного лица',
                    riskTypeName: 'С(ДТП)',
                    forIndividualTypeName: 'Застрахованное лица'
                },
                {
                    riskTypeId: 7,
                    forIndividualTypeId: 1,
                    name: 'Инвалидность застрахованного лица',
                    riskTypeName: 'Инвалидность',
                    forIndividualTypeName: 'Застрахованное лица'
                }
            ]);*/
        $http.get('http://localhost:8080/lookup/insurancescheme/' + schemeId + '/risk').then(
            function (response) {
                $log.log('got risk for scheme '+ schemeId +' from rest.');
                $log.log(response);
                success (response.data);
            }
        );
    };
    this.getInsuranceSchemes = function (success) {
        $http.get('http://localhost:8080/lookup/insurancescheme/').then(
            function (response) {
                $log.log('got Schemes from rest.');
                $log.log(response);
                success (response.data);
            }
        );
    };
    this.getInsuranceSchemeRules = function (success) {
        $http.get('http://localhost:8080/lookup/insuranceschemerule/').then(
            function (response) {
                $log.log('got schemerule from rest.');
                $log.log(response);
                success (response.data);
            }
        );
    };
    this.getCurrencies = function (success) {
        $http.get('http://localhost:8080/lookup/currency/').then(
            function (response) {
                $log.log('got currency from rest.');
                $log.log(response);
                success (response.data);
            }
        );
    };
    this.getFrequencies = function (success) {
        $http.get('http://localhost:8080/lookup/frequency/').then(
            function (response) {
                $log.log('got frequency from rest.');
                $log.log(response);
                success (response.data);
            }
        );
    };
    this.getRegions = function (success) {
        $http.get('http://localhost:8080/lookup/region/').then(
            function (response) {
                $log.log('got region from rest.');
                $log.log(response);
                success (response.data);
            }
        );
    };
    this.getGenders = function (success) {
        $http.get('http://localhost:8080/lookup/gender/').then(
            function (response) {
                $log.log('got gender from rest.');
                $log.log(response);
                success (response.data);
            }
        );
    };
});