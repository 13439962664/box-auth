var authJSON = {"moduleName":"认证/身份模块","funGroup":[]};
moduleJSON.push(authJSON);

var authFunGroup = {"funGroupName":"认证相关","fun":[]};
authJSON.funGroup.push(authFunGroup);
authFunGroup.fun.push({"funId":"login","funName":"登录","funParam":{"url":"./login","data":{"userName":"admin","password":"123456"}}});
authFunGroup.fun.push({"funId":"logout","funName":"退出","funParam":{"url":"./logout","data":{}}});

var authUserFunGroup = {"funGroupName":"用户管理","fun":[]};
authJSON.funGroup.push(authUserFunGroup);
authUserFunGroup.fun.push({"funId":"user.query","funName":"查询用户","funParam":{"url":"./auth/user/query","data":{"pageIndex":"1","pageSize":"10","userName":""}}});
authUserFunGroup.fun.push({"funId":"user.query.del","funName":"查询用户(无效)","funParam":{"url":"./auth/user/query","data":{"pageIndex":"1","pageSize":"10","del_":"1"}}});
authUserFunGroup.fun.push({"funId":"user.get","funName":"获取用户","funParam":{"url":"./auth/user/get","data":{"id":"1"}}});

authUserFunGroup.fun.push({"funId":"user.create","funName":"创建用户","funParam":{"url":"./auth/user/create","data":{"id":"","userName":"用户名","password":"123456"}}});
authUserFunGroup.fun.push({"funId":"user.updatePassword","funName":"修改密码","funParam":{"url":"./auth/user/updatePassword","data":{"id":"","password":"123456","oldPassword":"123456"}}});
authUserFunGroup.fun.push({"funId":"user.removeBatch","funName":"删除用户","funParam":{"url":"./auth/user/removeBatch","data":{"ids":"1"}}});
authUserFunGroup.fun.push({"funId":"user.recoveryBatch","funName":"恢复用户","funParam":{"url":"./auth/user/recoveryBatch","data":{"ids":"1"}}});
authUserFunGroup.fun.push({"funId":"user.possessRole","funName":"查看用户拥有的角色","funParam":{"url":"./auth/user/possessRole","data":{"userId":"1"}}});
authUserFunGroup.fun.push({"funId":"user.addRoleBatch","funName":"添加用户拥有的角色","funParam":{"url":"./auth/user/addRoleBatch","data":{"id":"1","roleIds":"10001","roleIds":"10002"}}});
authUserFunGroup.fun.push({"funId":"user.removeRoleBatch","funName":"移除用户拥有的角色","funParam":{"url":"./auth/user/removeRoleBatch","data":{"id":"1","roleIds":"10001","roleIds":"10002"}}});
authUserFunGroup.fun.push({"funId":"user.possessPermissions","funName":"查看用户拥有的权限","funParam":{"url":"./auth/user/possessPermissions","data":{"userId":"1"}}});

var authRoleFunGroup = {"funGroupName":"角色管理","fun":[]};
authJSON.funGroup.push(authRoleFunGroup);
authRoleFunGroup.fun.push({"funId":"role.query","funName":"查询角色","funParam":{"url":"./auth/role/query","data":{"pageIndex":"1","pageSize":"10"}}});
authRoleFunGroup.fun.push({"funId":"role.query.del","funName":"查询角色(无效)","funParam":{"url":"./auth/role/query","data":{"pageIndex":"1","pageSize":"10","del_":"1"}}});
authRoleFunGroup.fun.push({"funId":"role.get","funName":"获取角色","funParam":{"url":"./auth/role/get","data":{"id":"1"}}});
authRoleFunGroup.fun.push({"funId":"role.create","funName":"创建角色","funParam":{"url":"./auth/role/create","data":{"id":"","roleCode":"角色代码","roleName":"角色名称"}}});
authRoleFunGroup.fun.push({"funId":"role.update","funName":"修改角色","funParam":{"url":"./auth/role/update","data":{"id":"","roleCode":"角色代码","roleName":"角色名称"}}});
authRoleFunGroup.fun.push({"funId":"role.removeBatch","funName":"删除角色","funParam":{"url":"./auth/role/removeBatch","data":{"ids":"1"}}});
authRoleFunGroup.fun.push({"funId":"role.recoveryBatch","funName":"恢复角色","funParam":{"url":"./auth/role/recoveryBatch","data":{"ids":"1"}}});
authRoleFunGroup.fun.push({"funId":"role.possessPermissions","funName":"查看角色拥有的权限","funParam":{"url":"./auth/role/possessPermissions","data":{"roleId":"1"}}});
authRoleFunGroup.fun.push({"funId":"role.addPermissionsBatch","funName":"添加角色拥有的权限","funParam":{"url":"./auth/role/addPermissionsBatch","data":{"id":"1","permissionsIds":"10001","permissionsIds":"10002"}}});
authRoleFunGroup.fun.push({"funId":"role.removePermissionsBatch","funName":"移除角色拥有的权限","funParam":{"url":"./auth/role/removePermissionsBatch","data":{"id":"1","permissionsIds":"10001","permissionsIds":"10002"}}});
authRoleFunGroup.fun.push({"funId":"role.possessUser","funName":"查看角色对应的用户","funParam":{"url":"./auth/role/possessUser","data":{"roleId":"1"}}});
authRoleFunGroup.fun.push({"funId":"role.addUserBatch","funName":"添加角色对应的用户","funParam":{"url":"./auth/role/addUserBatch","data":{"id":"1","userIds":"10001","userIds":"10002"}}});
authRoleFunGroup.fun.push({"funId":"role.removeUserBatch","funName":"移除角色对应的用户","funParam":{"url":"./auth/role/removeUserBatch","data":{"id":"1","userIds":"10001","userIds":"10002"}}});

var authPermissionsFunGroup = {"funGroupName":"权限管理","fun":[]};
authJSON.funGroup.push(authPermissionsFunGroup);
authPermissionsFunGroup.fun.push({"funId":"permissions.query","funName":"查询权限","funParam":{"url":"./auth/permissions/query","data":{"pageIndex":"1","pageSize":"10"}}});
authPermissionsFunGroup.fun.push({"funId":"permissions.query.del","funName":"查询权限(无效)","funParam":{"url":"./auth/permissions/query","data":{"pageIndex":"1","pageSize":"10","del_":"1"}}});
authPermissionsFunGroup.fun.push({"funId":"permissions.get","funName":"获取权限","funParam":{"url":"./auth/permissions/get","data":{"id":"1"}}});
authPermissionsFunGroup.fun.push({"funId":"permissions.create","funName":"创建权限","funParam":{"url":"./auth/permissions/create","data":{"id":"","permissionsCode":"权限代码","permissionsName":"权限名称"}}});
authPermissionsFunGroup.fun.push({"funId":"permissions.update","funName":"修改权限","funParam":{"url":"./auth/permissions/update","data":{"id":"","permissionsCode":"权限代码","permissionsName":"权限名称"}}});
authPermissionsFunGroup.fun.push({"funId":"permissions.removeBatch","funName":"删除权限","funParam":{"url":"./auth/permissions/removeBatch","data":{"ids":"1"}}});
authPermissionsFunGroup.fun.push({"funId":"permissions.recoveryBatch","funName":"恢复权限","funParam":{"url":"./auth/permissions/recoveryBatch","data":{"ids":"1"}}});
authPermissionsFunGroup.fun.push({"funId":"permissions.createAndPossessRoleBatch","funName":"创建权限同时授予角色","funParam":{"url":"./auth/permissions/createAndPossessRoleBatch","data":{"permissionsCode":"test","permissionsName":"test","roleIds":"10001","roleIds":"10002"}}});
authPermissionsFunGroup.fun.push({"funId":"permissions.possessRole","funName":"查看权限对应的角色","funParam":{"url":"./auth/permissions/possessRole","data":{"permissionsId":"1"}}});
authPermissionsFunGroup.fun.push({"funId":"permissions.addRoleBatch","funName":"添加权限对应的角色","funParam":{"url":"./auth/permissions/addRoleBatch","data":{"id":"1","roleIds":"10001","roleIds":"10002"}}});
authPermissionsFunGroup.fun.push({"funId":"permissions.removeRoleBatch","funName":"移除权限对应的角色","funParam":{"url":"./auth/permissions/removeRoleBatch","data":{"id":"1","roleIds":"10001","roleIds":"10002"}}});
authPermissionsFunGroup.fun.push({"funId":"permissions.possessUser","funName":"查看拥有权限的用户","funParam":{"url":"./auth/permissions/possessUser","data":{"permissionsId":"1"}}});