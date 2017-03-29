var Realm = require('realm'); 

// Realm 테이블 설정값
let UserSchema = { 
	name : 'User', 
	properties : { 
		name : 'string', 
		email : 'string', 
		tel : 'string', 
		date : 'date' 
	} 
}; 

// 위에서 정의한 Realm 테이블의 설정값을 사용해서 테이블을 생성한다.
var UserRealm = new Realm({ 
	path : 'user.realm', 
	schema : [UserSchema] 
});

module.exports = { 
	UserRealm : UserRealm 
};


