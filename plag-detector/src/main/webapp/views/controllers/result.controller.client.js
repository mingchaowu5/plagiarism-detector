
(function(){
    angular
        .module("PlagApp")
        .controller("resultController", resultController);

    function resultController(ResultsService,$location, $routeParams,$rootScope,$scope) {
         var vm = this;
         vm.assignmentID = $routeParams.aid;

        var nodes = null;
        var edges = null;
        var network = null;
        var THRESHOLDVALUE = 30;

        function init() {
            var promise = ResultsService.assignmentResults(vm.assignmentID);

            promise
                .then(function (params) {
                    if(params.data){
                        nodes = params.data.nodes;
                        edges = params.data.edges;
                        draw();
                    }
                })
                .catch(function (err) {
                    console.log("Error in fetching result for the assignemnt ID -> "+ vm.assignmentID);
                })
        }
        // init()

        function draw() {
            
            // nodes = [
            //     {id: 1,  value: 2,  label: 'Algie' },
            //     {id: 2,  value: 31, label: 'Alston'},
            //     {id: 3,  value: 12, label: 'Barney'},
            //     {id: 4,  value: 16, label: 'Coley' },
            //     {id: 5,  value: 17, label: 'Grant' },
            //     {id: 6,  value: 15, label: 'Langdon'},
            //     {id: 7,  value: 6,  label: 'Lee'},
            //     {id: 8,  value: 5,  label: 'Merlin'},
            //     {id: 9,  value: 35, label: 'Mick'},
            //     {id: 10, value: 18, label: 'Tod'},
            // ];

            
            // edges = [
            //     {from: 2, to: 8, value: 3},
            //     {from: 2, to: 9, value: 5},
            //     {from: 2, to: 10,value: 1},
            //     {from: 4, to: 6, value: 8},
            //     {from: 5, to: 7, value: 2},
            //     {from: 4, to: 5, value: 1},
            //     {from: 9, to: 10,value: 2},
            //     {from: 2, to: 3, value: 6},
            //     {from: 3, to: 9, value: 4},
            //     {from: 5, to: 3, value: 1},
            //     {from: 2, to: 7, value: 4}
            // ];

            nodes.forEach(function (element) {
                if(element.value > THRESHOLDVALUE){
                    element.color = 'pink'
                }
            })

            // Instantiate our network object.
            var container = document.getElementById('mynetwork');
            var data = {
                nodes: nodes,
                edges: edges
            };
            var options = {
                nodes: {
                    shape: 'dot',
                    scaling: {
                        customScalingFunction: function (min,max,total,value) {
                            return value/total;
                        },
                        min:5,
                        max:150
                    }
                }
            };
            network = new vis.Network(container, data, options);
            // console.log(network);
            network.on('select', OnClick);
        }

        function OnClick(params) {
            console.log(params);
            console.log(edges);
            console.log(nodes);
            
            if(params.edges.length == 1 && params.nodes.length == 0){
                edges.forEach(function(element) {
                    if(element.id == params.edges[0]){
                        vm.selectedEdgeInfo = element;

                        nodes.forEach(elementLow => {
                            if(element.to == elementLow.id){
                                console.log(elementLow.label);
                                vm.selectedNodeInfo = elementLow.label;
                                 $scope.$apply();                        
                                
                            }
                        });

                        nodes.forEach(elementLow => {
                            if(element.from == elementLow.id){
                                console.log(elementLow.label);
                                vm.selectedNodeInfo2 = elementLow.label;
                        $scope.$apply();                        
                                
                            }
                        });
                        vm.edgePercentage = element.value;
                        document.getElementById("myButton").click();
                    }
                })
            }
            else{
                nodes.forEach(element => {
                    if(params.nodes[0] == element.id){
                        console.log(element.label);
                        document.getElementById("myButton").click();
                        vm.selectedNodeInfo = element.label;
                        vm.nodePercentage = element.value;
                        $scope.$apply();
                    }
                });
            }
            
            // element = fetchNodeWithID(nodes, params.nodes[0])
            
            
            
            
            
            // document.getElementById('selection').innerHTML = 'Selection: ' + params.nodes;
        }

        function fetchNodeWithID(nodes, id) {
            nodes.forEach(element => {
                if(id == element.id){
                    return element
                }
            });
        }
        draw();
    }
})();