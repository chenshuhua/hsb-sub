const HeaderName = "A-TOKEN";
// var wand = "";
var userName = "almare";
var userId = "1";

export function getToken() {
    // return wand;
    return localStorage.getItem(HeaderName);
}

export function setToken(token) {
    // wand = token;
    return localStorage.setItem(HeaderName, token);
}

export function removeToken() {
    // wand = "";
    return localStorage.removeItem(HeaderName);
}

export function setUerName(name){
    userName = name;
}

export function getUserName(){
    return userName;
}

export function setUserId(id){
    userId = id;
}

export function getUserId(){
    return userId;
}