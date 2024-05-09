var input_el = document.querySelectorAll("input");
input_el.forEach(e => {
    e.addEventListener("keyup", function(f){
    // console.log(f);
    if(f.key == "Enter"){
        document.getElementById("nextBtn").click();
    }
});
});

//=============================================================================
var currentTab = 0; // Current tab is set to be the first tab (0)
showTab(currentTab); // Display the current tab

function showTab(n) {
// This function will display the specified tab of the form ...
var x = document.getElementsByClassName("tab");
x[n].style.display = "block";
// ... and fix the Previous/Next buttons:
if (n == 0) {
    document.getElementById("prevBtn").style.display = "none";
} else {
    document.getElementById("prevBtn").style.display = "inline";
}
if (n == (x.length - 1)) {
    document.getElementById("nextBtn").innerHTML = "Submit";
} else {
    document.getElementById("nextBtn").innerHTML = "→";
}
// ... and run a function that displays the correct step indicator:
fixStepIndicator(n)
}

function nextPrev(n) {
    // This function will figure out which tab to display
    var x = document.getElementsByClassName("tab");
    // Exit the function if any field in the current tab is invalid:
    if (n == 1 && !validateForm()) return false;

    if (currentTab === 1 && n === 1) {
        var pass1 = document.getElementById("ps1").value;
        var pass2 = document.getElementById("ps2").value;
        if (pass1 !== pass2) {
            alert("您輸入的兩個密碼並不相符，請再試一次。");
            return false;
        }
    }

    // Hide the current tab:
    x[currentTab].style.display = "none";
    // Increase or decrease the current tab by 1:
    currentTab = currentTab + n;
    // if you have reached the end of the form... :
    if (currentTab >= x.length) {
        //...the form gets submitted:
        document.getElementById("regForm").submit();
        return false;
    }
    // Otherwise, display the correct tab:
    showTab(currentTab);



}

function validateForm() {
    // This function deals with validation of the form fields
    var x, y, i, valid = true;
    x = document.getElementsByClassName("tab");
    y = x[currentTab].getElementsByTagName("input");
    // A loop that checks every input field in the current tab:
    for (i = 0; i < y.length; i++) {
        // If a field is empty...
        if (y[i].value == "") {
        // add an "invalid" class to the field:
        y[i].className += " invalid";
        // and set the current valid status to false:
        valid = false;
        }
    }
    // If the valid status is true, mark the step as finished and valid:
    if (valid) {
        document.getElementsByClassName("step")[currentTab].className += " finish";
    }
    return valid; // return the valid status
}

function fixStepIndicator(n) {
// This function removes the "active" class of all steps...
var i, x = document.getElementsByClassName("step");
for (i = 0; i < x.length; i++) {
    x[i].className = x[i].className.replace(" active", "");
}
//... and adds the "active" class to the current step:
x[n].className += " active";
}

//=============================================================================

function eyeSwitch(i) {
    switch (i){
        case 0:
            if((document.getElementById("ps1").type) ==="password"){
                document.getElementById("ps1").type ="text";
                document.getElementById("eye1").src = "/front_end/mem/img/eye-slash.svg";
               
            }else{
                document.getElementById("ps1").type ="password";
                document.getElementById("eye1").src = "/front_end/mem/img/eye.svg";
            }
            break;

            case 1:
                if((document.getElementById("ps2").type) ==="password"){
                    document.getElementById("ps2").type ="text";
                    document.getElementById("eye2").src = "/front_end/mem/img/eye-slash.svg";
                   
                }else{
                    document.getElementById("ps2").type ="password";
                    document.getElementById("eye2").src = "/front_end/mem/img/eye.svg";
                }
            break;
    }

}

//=======================
function validate(){

    if (currentTab === 1 && n === 1) {
        var pass1 = document.getElementById("ps1").value;
        var pass2 = document.getElementById("ps2").value;
        if (pass1 !== pass2) {
            alert("Passwords do not match!");
            return false;
        }
    }
}

///////////////////////////////////////////////////



// 帳號唯一性驗證函數
function checkUniqueAccount() {
    let account = document.getElementById("memAccount1").value;
    
    $.ajax({
        url: "/checkAccount",
        type: "GET",
        data: { memAcount: account },
        success: function(response) {
            if (response.exists) {
                // 如果帐号已存在，显示错误消息
                document.getElementById("Error").innerText = "已存在之會員帳號";
            } else {
                // 如果帐号不存在，清除错误消息
                document.getElementById("Error").innerText = "";
            }
        },

    });
}

// 郵件地址唯一性驗證函數
function checkUniqueEmail() {
    let email = document.getElementById("memEmail1").value;
    // 向後端發送郵件地址驗證請求
    $.ajax({
        url: "/checkEmail",
        type: "GET",
        data: {memEmail: email},
        success: function(response) {
            // 在成功接收到後端響應後處理
            if (response.exists) {
                // 如果郵件地址已存在，顯示錯誤消息
                document.getElementById("Error").innerText = "已存在之會員信箱";
            } else {
                // 如果郵件地址不存在，清除錯誤消息
                document.getElementById("Error").innerText = "";
            }
        }
    });
}


// 在下面添加调用帐号和邮箱地址唯一性验证函数的逻辑
// 获取帐号输入框
var accountInput = document.getElementById("memAccount1");
// 绑定失去焦点事件，调用帐号唯一性验证函数

accountInput.addEventListener("blur", checkUniqueAccount);

// 获取邮箱地址输入框
var emailInput = document.getElementById("memEmail1");
// 绑定失去焦点事件，调用邮箱地址唯一性验证函数
emailInput.addEventListener("blur", checkUniqueEmail);