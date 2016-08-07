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
    //riskDataSet.selectedRisk = riskDataSet.data[0];

    function getPayFreqCount(payFreq) {
        var payFreqCount = null;
        switch (payFreq) {
            case 'E':
                payFreqCount = 1;
                break;
            case 'Y':
                payFreqCount = 1;
                break;
            case 'Q':
                payFreqCount = 4;
                break;
            case 'H':
                payFreqCount = 2;
                break;
            case 'M':
                payFreqCount = 12;
                break;
            default:
                payFreqCount = null;
        }
        return payFreqCount;
    }
    var service = {
        getRiskData: function (setResult) {
            setResult(riskDataSet.data);
        },

        getPayCount: function (payTerm, payFreq) {
            $log.debug("payFreq" + payFreq);
            $log.debug("payTerm" + payTerm);
            var payFreqCount = getPayFreqCount(payFreq && payFreq.code);

            return payTerm * payFreqCount;
        },

        getEventRisksForScheme: function (schemeId, success) {
            $http.get('http://localhost:8080/lookup/insurancescheme/' + schemeId + '/risk'
            ).then(
                function (response) {
                    $log.log('got risk for scheme ' + schemeId + ' from rest.');
                    $log.log(response);
                    success(response.data);
                }
            );
        },

        getInsuranceSchemes: function (success) {
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
        },

        getInsuranceSchemeRules: function (success) {
            $http.get('http://localhost:8080/lookup/insuranceschemerule/').then(
                function (response) {
                    $log.log('got schemerule from rest.');
                    $log.log(response);
                    success(response.data);
                }
            );
        },

        getCurrencies: function (success) {
            $http.get('http://localhost:8080/lookup/currency/').then(
                function (response) {
                    $log.log('got currency from rest.');
                    $log.log(response);
                    success(response.data);
                }
            );
        },

        getFrequencies: function (success) {
            $http.get('http://localhost:8080/lookup/frequency/').then(
                function (response) {
                    $log.log('got frequency from rest.');
                    $log.log(response);
                    success(response.data);
                }
            );
        },

        getRegions: function (success) {
            $http.get('http://localhost:8080/lookup/region/').then(
                function (response) {
                    $log.log('got region from rest.');
                    $log.log(response);
                    success(response.data);
                }
            );
        },

        getGenders: function (success) {
            $http.get('http://localhost:8080/lookup/gender/').then(
                function (response) {
                    $log.log('got gender from rest.');
                    $log.log(response);
                    success(response.data);
                }
            );
        },

        performCalc: function (calcData, riskData) {

        }
    };

    return service;
});