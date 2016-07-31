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
                nettoTariff: "0.0001",
                payCount: 1
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
                nettoTariff: "0.0001",
                payCount: 1
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
                nettoTariff: "0.000133",
                payCount: 1
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
                nettoTariff: "0.000221",
                payCount: 1
            }
        ]
    };
    riskDataSet.selectedRisk = riskDataSet.data[0];

    this.getRiskData = function (setResult) {
        setResult(riskDataSet.data);
    };

    function getPayFreqCount (payFreq) {
       var  payFreqCount = null;
       switch (payFreq) {
           case 'E': payFreqCount = 1; break;
           case 'Y': payFreqCount = 1; break;
           case 'Q': payFreqCount = 4; break;
           case 'H': payFreqCount = 2; break;
           case 'M': payFreqCount = 12; break;
       }
        return payFreqCount;
    }
    this.getPayCount = function (payTerm, payFreq) {
        $log.debug("payFreq"+payFreq);
        $log.debug("payTerm"+payTerm);

        return payTerm * getPayFreqCount(payFreq.code);
    };
    //this.setSelectedRisk = function (risk) {
    //    riskDataSet.selectedRisk = risk;
    //};
    //this.isRiskSelected = function (risk) {
    //    return riskDataSet.selectedRisk == risk;
    //};

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
        $http.get('http://localhost:8080/lookup/insurancescheme/', {
                //params: {'username': 'admin', 'password': 'admin'}
               // params: {'token': securityService.getToken()}
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