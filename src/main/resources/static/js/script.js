console.log("Script loaded");

let currentTheme=getTheme();
changeTheme();

// document.addEventListener("DOMContentLoaded"),()=>{
//     changeTheme();
// }

//TODO:
function changeTheme(){
    // set to web page
    document.querySelector('html').classList.add(currentTheme);

    // set the eventListener to change theme button
    const changeThemeButton=document.querySelector('#theme_change_button');
    changeThemeButton.querySelector("span").textContent = 
    currentTheme == "light" ? "Dark" : "Light";
    changeThemeButton.addEventListener("click",(event)=>{
        // remove the current theme
        document.querySelector('html').classList.remove(currentTheme);
        if(currentTheme === "dark"){
            currentTheme="light";
        }
        else {
            currentTheme="dark";
        }
        // update in the localStorage
        setTheme(currentTheme);
        // set the current theme
        document.querySelector('html').classList.add(currentTheme);
        
        //change the text of the button accordingly
        changeThemeButton.querySelector("span").textContent = 
        currentTheme == "light" ? "Dark" : "Light";
    });
}

//set theme to localstorage
function setTheme(theme){
    localStorage.setItem("theme", theme);
}

//get theme from localStorage
function getTheme() {
    let theme=localStorage.getItem("theme");
    return theme ? theme : "light";
}