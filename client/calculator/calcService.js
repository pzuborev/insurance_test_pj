app.service('calcService', function ($http, $log) {

    var riskDataSet = {
        data: [
            {
                rowNo: 1,
                riskType: {id: "1", "name": "Д"},
                forIndividualType: {id: "1", name: "Застрахованное лицо"},
                riskAmount: "2000",
                payAmount: "500",
                term: "10",
                payTerm: "10",
                nettoTariff: "0.0001",
                payCount: 1
            },
            {
                rowNo: 2,
                riskType: {id: "2", "name": "C"},
                forIndividualType: {id: "1", name: "Застрахованное лицо"},
                riskAmount: "2000",
                payAmount: "500",
                term: "10",
                payTerm: "10",
                nettoTariff: "0.0001",
                payCount: 1
            },
            {
                rowNo: 3,
                riskType: {id: "3", "name": "C(НС)"},
                forIndividualType: {id: "1", name: "Застрахованное лицо"},
                riskAmount: "200",
                payAmount: "1",
                term: "10",
                payTerm: "10",
                nettoTariff: "0.000133",
                payCount: 1
            },
            {
                rowNo: 4,
                riskType: {id: "4", "name": "С(ДТП)"},
                forIndividualType: {id: "1", name: "Застрахованное лицо"},
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
            var payFreqCount = getPayFreqCount(payFreq && payFreq.code);

            return payTerm * payFreqCount;
        },

        getEventRisksForScheme: function (schemeId, success) {
            $http.get('/api/lookup/insurancescheme/' + schemeId + '/risk'
            ).then(
                function (response) {
                    $log.log('got risk for scheme ' + schemeId + ' from rest.');
                    //$log.log(response);
                    success(response.data);
                }
            );
        },

        getInsuranceSchemes: function (success) {
            console.log("*** getInsuranceSchemes");
            $http.get('/api/lookup/insurancescheme/').then(
                function (response) {
                    $log.log('got Schemes from rest.');
                    $log.log(response);
                    success(response.data);
                }
            );
        },

        getInsuranceSchemeRules: function (success) {
            $http.get('/api/lookup/insuranceschemerule/').then(
                function (response) {
                    $log.log('got schemerule from rest.');
                    $log.log(response);
                    success(response.data);
                }
            );
        },

        getCurrencies: function (success) {
            $http.get('/api/lookup/currency/').then(
                function (response) {
                    $log.log('got currency from rest.');
                    $log.log(response);
                    success(response.data);
                }
            );
        },

        getFrequencies: function (success) {
            $http.get('/api/lookup/frequency/').then(
                function (response) {
                    $log.log('got frequency from rest.');
                    $log.log(response);
                    success(response.data);
                }
            );
        },

        getRegions: function (success) {
            $http.get('/api/lookup/region/').then(
                function (response) {
                    $log.log('got region from rest.');
                    $log.log(response);
                    success(response.data);
                }
            );
        },

        getGenders: function (success) {
            $http.get('/api/lookup/gender/').then(
                function (response) {
                    $log.log('got gender from rest.');
                    $log.log(response);
                    success(response.data);
                }
            );
        },

        performCalc: function (calcData, riskData, onSuccess) {
            var data = calcData;
            data.risks = riskData;
            $log.debug(riskData);
            $log.debug(data.risks);
            $http({
                method: 'POST',
                url: '/api/calculator/',
                data: data,
                headers: {'Content-Type': 'application/json'}
            }).then(function (result) {
                $log.debug(result && result.data && result.data.risks);
                onSuccess(result && result.data && result.data.risks);
            }, function (error) {
                $log.error(error);
            });

        }
    };

    return service;
});