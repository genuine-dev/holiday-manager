// app.js

var app = new Vue({
        el: "#app-root",

        data: {
            users: [
                {
                    id: 0,
                    login: "user1",
                    name: "ユーザ名1",
                    email: "example@mail.com",
                    edit: false
                },
                {
                    id: 1,
                    login: "user2",
                    name: "ユーザ名2",
                    email: "example@mail.com",
                    edit: false
                },
                {
                    id: 2,
                    login: "user3",
                    name: "ユーザ名3",
                    email: "example@mail.com",
                    edit: false
                }
            ],

            user: {
                id: -1,
                login: "",
                name: "",
                email: ""
            },

            editUserId: -1,

            validation: {
                login: false,
                name: false,
                email: false,
                msg: {
                    login: "未入力です",
                    name: "未入力です",
                    email: "未入力です"
                }
            },

            validator: {}
        },

        computed: {
            isValid: function(){
                var valid = true;
                for (var key in this.validation) {
                    if (!this.validation[key]) {
                        valid = false;
                    }
                }
                return valid;
            }
        },

        created: function(){
        },

        filters: {
            loginValidator: {
                write: function(val){
                    var validation = this.validation;

                    if( isEmpty(val) ){
                        validation.login = false;
                        this.validation.msg.login = "未入力です";
                    }else if( !isAlphaNumeric(val) ){
                        validation.login = false;
                        this.validation.msg.login = "半角英数字で入力して下さい";
                    }else{
                        validation.login = true;
                    }
                    return val;
                }
            },
            nameValidator: {
                write: function(val){
                    var validation = this.validation;

                    if( isEmpty(val) ){
                        validation.name = false;
                        validation.msg.name = "未入力です";
                    }else if( val.length < 4 ){
                        validation.name = false;
                        validation.msg.name = "4文字以上必要です";
                    }else{
                        validation.name = true;
                    }
                    return val;
                }
            },
            emailValidator: {
                write: function(val){
                    var validation = this.validation;

                    if( isEmpty(val) ){
                        validation.email = false;
                        validation.msg.email = "未入力です";
                    }else if( !isEmail(val) ){
                        validation.email = false;
                        validation.msg.email = "形式が正しくありません";
                    }else{
                        validation.email = true;
                    }
                    return val;
                }
            }
        },

        methods: {
            userMouseOver: function(){
                console.log("mouseover", arguments);
            },

            // ユーザの更新・作成
            updateUser: function(e){
                e.preventDefault();

                // 入力内容にエラーがあればスルー
                if( !this.isValid ){
                    return;
                }

                // 登録用ユーザ生成
                var user = {
                    id: this.user.id,
                    name: this.user.name,
                    login: this.user.login,
                    email: this.user.email,
                    edit: false
                };

                // create
                if( user.id < 0 || user.id === false ){
                    user.id = parseInt(this.users[ this.users.length - 1 ].id) + 1;
                    this.users.push( user );
                    this.releaseEditState();

                // update
                }else{
                    var currentUser = this.getUserById(user.id);
                    currentUser.name = user.name;
                    currentUser.login = user.login;
                    currentUser.email = user.email;
                }
            },

            // ユーザの選択
            selectUser: function(user){
                // 編集中のユーザなら、編集を解除
                if( this.editUserId == user.id ){
                    this.releaseCurrentUser();
                    this.releaseEditState();

                // それ以外は編集状態へ
                }else{
                    this.releaseEditUser(this.getUserById(this.editUserId));
                    this.setCurrentUser(user);
                }
            },

            // idを指定したユーザを取得
            getUserById: function(id){
                var user = _.select(this.users, function(obj){
                    return obj.id == id;
                });
                return user[0];
            },

            // 指定のユーザを編集状態へ
            setCurrentUser: function(user){
                user.edit = true;
                this.user.id = this.editUserId = user.id;
                this.user.name = user.name;
                this.user.login = user.login;
                this.user.email = user.email;

                this.validation.login = true;
                this.validation.name = true;
                this.validation.email = true;
            },

            // ユーザの編集状態を解除
            releaseEditUser: function(user){
                if( user ) user.edit = false;
            },

            // 編集中のユーザを解除
            releaseCurrentUser: function(){
                if( this.editUserId > -1 ){
                    this.releaseEditUser( this.getUserById(this.editUserId) );
                }
            },

            // 編集状態を解除
            releaseEditState: function(){
                this.editUserId = -1;
                this.user.id = -1;
                this.user.name = "";
                this.user.login = "";
                this.user.email = "";

                this.validation.login = false;
                this.validation.name = false;
                this.validation.email = false;
                this.isValid = false;
            },

            // 指定したidのユーザを削除
            deleteUserById: function(id){
                this.deleteUser(this.getUserById(id));
            },

            // ユーザを削除
            deleteUser: function(user){
                var _this = this;

                // 編集中のユーザなら先に編集状態を開放
                if( _this.editUserId == user.id ){
                    _this.releaseEditUser(user);
                    _this.releaseEditState();
                }

                // 対象ユーザを削除
                _.some(_this.users, function(obj, i){
                    if( obj && obj.id == user.id ){
                        _this.users.$remove(obj);
                        return false;
                    }
                });
            }
        }
    });

    function isEmpty(value){
        return !value || value == "";
    }

    function isAlphaNumeric(value){
        return value.match(/^[0-9a-zA-Z_-]+$/) ? true : false;
    }

    function isEmail(value){
        var emailRe = /^(([^<>()[].,;:s@"]+(.[^<>()[].,;:s@"]+)*)|(".+"))@(([[0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}])|(([a-zA-Z-0-9]+.)+[a-zA-Z]{2,}))$/;
        return emailRe.test(value);
    }
