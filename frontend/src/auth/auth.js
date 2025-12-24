

export const saveToken=(token)=>{
 localStorage.setItem('jwt_token',token);
}

export const getToken=()=>{
    return localStorage.getItem('jwt_token');
}

export const removeToken=()=>{
    localStorage.removeItem('jwt_token');
}

export const saveUserId=(userId)=>{
    return localStorage.setItem('user_id',userId);
}

export const getUserId=()=>{
    return localStorage.getItem('user_id');
}

export const removeUserid=()=>{
    localStorage.removeItem();
}