app.service('calcService', function ($http, $log, authService) {

    var riskDataSet = {
        data: [
            //{
            //    rowNo: 1,
            //    riskTypeId: "1",
            //    forIndividualTypeId: "1",
            //    riskTypeName: "Д",
            //    forIndividualTypeName: "Застрахованное лицо",
            //    riskAmount: "2000",
            //    payAmount: "500",
            //    term: "10",
            //    payTerm: "10",
            //    nettoTariff: "0.0001"
            //},
            //{
            //    rowNo: 2,
            //    riskTypeId: "2",
            //    forIndividualTypeId: "1",
            //    riskTypeName: "C",
            //    forIndividualTypeName: "Застрахованное лицо",
            //    riskAmount: "2000",
            //    payAmount: "500",
            //    term: "10",
            //    payTerm: "10",
            //    nettoTariff: "0.0001"
            //},
            //{
            //    rowNo: 3,
            //    riskTypeId: "3",
            //    forIndividualTypeId: "1",
            //    riskTypeName: "C(НС)",
            //    forIndividualTypeName: "Застрахованное лицо",
            //    riskAmount: "200",
            //    payAmount: "1",
            //    term: "10",
            //    payTerm: "10",
            //    nettoTariff: "0.000133"
            //},
            //{
            //    rowNo: 4,
            //    riskTypeId: "4",
            //    forIndividualTypeId: "1",
            //    riskTypeName: "C(ДТП)",
            //    forIndividualTypeName: "Застрахованное лицо",
            //    riskAmount: "1000",
            //    payAmount: "10",
            //    term: "10",
            //    payTerm: "10",
            //    nettoTariff: "0.000221"
            //}
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
        $http.get('http://localhost:8080/lookup/insurancescheme/' + schemeId + '/risk'
        ).then(
            function (response) {
                $log.log('got risk for scheme ' + schemeId + ' from rest.');
                $log.log(response);
                success(response.data);
            }
        );
    };
    this.getInsuranceSchemes = function (success) {
        console.log("*** getInsuranceSchemes");
        console.log(" authService.getToken "+ authService.getToken());


        $http.get('http://localhost:8080/lookup/insurancescheme/', {
                //params: {'username': 'admin', 'password': 'admin'}
               // params: {'token': authService.getToken()}
            }
        ).then(
            function (response) {
                $log.log('got Schemes from rest.');
                $log.log(response);
                success(response.data);
            }
        );
    };
    this.getInsuranceSchemeRules = function (success) {
        $http.get('http://localhost:8080/lookup/insuranceschemerule/').then(
            function (response) {
                $log.log('got schemerule from rest.');
                $log.log(response);
                success(response.data);
            }
        );
    };
    this.getCurrencies = function (success) {
        $http.get('http://localhost:8080/lookup/currency/').then(
            function (response) {
                $log.log('got currency from rest.');
                $log.log(response);
                success(response.data);
            }
        );
    };
    this.getFrequencies = function (success) {
        $http.get('http://localhost:8080/lookup/frequency/').then(
            function (response) {
                $log.log('got frequency from rest.');
                $log.log(response);
                success(response.data);
            }
        );
    };
    this.getRegions = function (success) {
        $http.get('http://localhost:8080/lookup/region/').then(
            function (response) {
                $log.log('got region from rest.');
                $log.log(response);
                success(response.data);
            }
        );
    };
    this.getGenders = function (success) {
        $http.get('http://localhost:8080/lookup/gender/').then(
            function (response) {
                $log.log('got gender from rest.');
                $log.log(response);
                success(response.data);
            }
        );
    };
});