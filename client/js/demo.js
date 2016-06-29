/**
 * You must include the dependency on 'ngMaterial'
 */
//    angular.module('BlankApp', ['ngMaterial']);

(function () {
    'use strict';
    angular
        .module('autocompleteDemo', ['ngMaterial'])
        .controller('DemoCtrl', DemoCtrl);
    function DemoCtrl ($timeout, $q, $log) {
        var self = this;
        self.simulateQuery = true;
        self.isDisabled    = false;
        // list of `state` value/display objects
        self.items        = loadAll();
        self.querySearch   = querySearch;
        self.selectedItemChange = selectedItemChange;
        self.searchTextChange   = searchTextChange;
        $log.info(loadAll());
        // ******************************
        // Internal methods
        // ******************************
        /**
         * Search for states... use $timeout to simulate
         * remote dataservice call.
         */
        function querySearch (query) {
            var results = query ? self.items.filter( createFilterFor(query) ) : self.items,
                deferred;
            if (self.simulateQuery) {
                deferred = $q.defer();
                $timeout(function () { deferred.resolve( results ); }, Math.random() * 1000, false);
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
        /**
         * Build `states` list of key/value pairs
         */
        function loadAll() {
            var allItems = 'Alabama, Alaska, Arizona, Arkansas, California, Colorado, Connecticut, Delaware,\
              Florida, Georgia, Hawaii, Idaho, Illinois, Indiana, Iowa, Kansas, Kentucky, Louisiana,\
              Maine, Maryland, Massachusetts, Michigan, Minnesota, Mississippi, Missouri, Montana,\
              Nebraska, Nevada, New Hampshire, New Jersey, New Mexico, New York, North Carolina,\
              North Dakota, Ohio, Oklahoma, Oregon, Pennsylvania, Rhode Island, South Carolina,\
              South Dakota, Tennessee, Texas, Utah, Vermont, Virginia, Washington, West Virginia,\
              Wisconsin, Wyoming';
            return allItems.split(/, +/g).map( function (item) {
                return {
                    value: item.toLowerCase(),
                    display: item
                };
            });
        }
        /**
         * Create filter function for a query string
         */
        function createFilterFor(query) {
            var lowercaseQuery = angular.lowercase(query);
            return function filterFn(item) {
                return (item.value.indexOf(lowercaseQuery) === 0);
            };
        }
    }
})();