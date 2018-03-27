angular.module("indexView",['ui.router'])
.config(function ($stateProvider) {
    var checkRestView={
        name:'checkRest',
        url:'/checkRest',
        templateUrl:'templates/checkRest.html'
    };

    var depositView = {
        name: 'deposit',
        url:'/deposit',
        templateUrl:'templates/deposit.html'
    };

    var withdrawView={
        name:'withdraw',
        url:'/withdraw',
        templateUrl:'templates/withdraw.html'
    };

    var transferView = {
        name: 'transfer',
        url: '/transfer',
        templateUrl: 'templates/transfer.html'
    };

    $stateProvider.state(checkRestView);
    $stateProvider.state(depositView);
    $stateProvider.state(withdrawView);
    $stateProvider.state(transferView);
}).controller("indexController",function ($scope,$http) {
    $scope.cardId = "0";
    $scope.data = {};

    $scope.checkRest=function (cardId) {
        if($scope.cardId==="0"){
            return;
        }
        $http.get('/checkRest/'+cardId).then(function (resp) {
            $scope.data = resp.data;
        },function (error) {
            console.log(error);
        })
    }

    $scope.deposit=function (cardId,depositMoney) {
        if($scope.cardId==="0"){
            return;
        }
        $http.get('/deposit/'+cardId+'/'+depositMoney).then(function (resp) {
            $scope.data = resp.data;
        },function (error) {
            console.log(error);
        })
    }

    $scope.withdraw=function (cardId,withdrawMoney) {
        if($scope.cardId==="0"){
            return;
        }
        $http.get('/withdraw/'+cardId+'/'+ withdrawMoney).then(function (resp) {
            $scope.data = resp.data;
        },function (error) {
            console.log(error);
        })
    }

    $scope.transfer=function (cardId, toCardId, transferMoney) {
        if($scope.cardId==="0"){
            return;
        }
        $http.get('/transfer/'+cardId+'/'+toCardId+'/'+transferMoney).then(function (resp) {
            $scope.data = resp.data;
        },function (error) {
            console.log(errror);
        })
    }

    $scope.loginIn=function (cardId,password) {
        $http.get('/loginIn/'+cardId+'/'+password).then(function (resp){
            $scope.data = resp.data;
        },function (error) {
            console.log(error);
        })
        if($scope.data.message==="successfully login in"){
            $scope.cardId = cardId;
        }
    }

    $scope.register=function (cardId,password) {
        $http.get('/register/'+cardId+'/'+password).then(function (resp){
            $scope.data = resp.data;
        },function (error) {
            console.log(error);
        })
        if($scope.data.message==="successfully register"){
            $scope.cardId = cardId;
        }
    }

    $scope.loginOut=function () {
        $scope.cardId="0";
    }

});
