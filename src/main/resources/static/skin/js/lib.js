function importJs(src) {
    var scriptElem = document.createElement('script');
    scriptElem.setAttribute('src', src);
    scriptElem.setAttribute('type', 'text/javascript');
    document.getElementsByTagName('head')[0].appendChild(scriptElem);
}

function importJsNoCache(src) {
    var ms = new Date().getTime().toString();
    var seed = "?" + ms;
    importJs(src + seed);
}

// отправка формы
function sendPostFrom(page, formName, handlerPost) {
    var param = getFormParam(formName);

    sendPOST(page, param, handlerPost);

}

// Копирование в буфер обмена

function writeText(text) {

    var textArea = document.createElement("textarea");
    textArea.value = text;
    document.body.appendChild(textArea);
    textArea.focus();
    textArea.select();

    try {
        document.execCommand('copy');
    } catch (err) {
        console.error('Unable to copy', err);
    }

    document.body.removeChild(textArea);

}


function getFormParam(formName) {
    var forms = document.forms[formName];
    var param = "";

    for (var i = 0; i < forms.length; i++) {


        if ("checkbox" === forms.elements[i].type && !forms.elements[i].checked) {
            continue;
        }

        if ("" !== forms.elements[i].name) {

            if ("" !== param) {
                param = param + "&";
            }

            param = param + forms.elements[i].name + "=" + forms.elements[i].value;
        }
    }
    return param;
}

function sendPOST(page, param, handler) {
    var xmlhttp;
    var result = "";

    if (window.XMLHttpRequest) {
        xmlhttp = getXmlHttp();
    }

    xmlhttp.open("POST", page, true);
    xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xmlhttp.send(param);
    xmlhttp.onreadystatechange = processRequestChange;


    function processRequestChange() {
        if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {
            result = xmlhttp.responseText;
            handler(result);
        }

    }

}


function getXmlHttp() {
    var xmlhttp;
    try {
        xmlhttp = new ActiveXObject("Msxml2.XMLHTTP");
    } catch (e) {
        try {
            xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
        } catch (E) {
            xmlhttp = false;
        }
    }
    if (!xmlhttp && typeof XMLHttpRequest !== 'undefined') {
        xmlhttp = new XMLHttpRequest();
    }
    return xmlhttp;
}


// если  подгрузили форму авторизации то обновляем страницу
function checkedIsLogin(){
    var formLogin = document.getElementById("formLogin");
    if(formLogin){
    location.reload();
    //window.location.replace("/");
    }
}

// id       -  row-Page
// linkHtml - list.html

class Page {

    // id       - id, куда загружаеть (row-Page)
    // linkHtml - путь (list.html)
    loadPage(id, linkHtml) {

        if (id === "" || linkHtml === "") return;

        var cont = document.getElementById(id);

        var http = this.createRequestObject();


        if (http)
        {
            http.open('get', linkHtml);
            http.onreadystatechange = function ()
            {
                if (http.readyState === 4)
                {
                    cont.innerHTML = http.responseText;
                checkedIsLogin();
                reloadAtEndOfPageLoad(linkHtml);
                }
            };
            http.send(null);
        } else
        {
            document.location = linkHtml;
        }
    }

    // id       - id, куда загружаеть (row-Page)
    // linkHtml - путь (list.html)
    loadPagePost(id, linkHtml, params) {

        if (id === "" || linkHtml === "" || params === "") return;

        var cont = document.getElementById(id);

        var http = this.createRequestObject();


        if (http)
        {
            http.open('POST', linkHtml);
            http.onreadystatechange = function ()
            {
                if (http.readyState === 4)
                {
                    cont.innerHTML = http.responseText;
                    checkedIsLogin();
                    reloadAtEndOfPageLoad(linkHtml);
                }

            };
            http.send(params);
        } else
        {
            document.location = linkHtml;
        }
    }

    loadPagePostJson(id, linkHtml, json) {

        if (id === "" || linkHtml === "" || json === "") return;

        var cont = document.getElementById(id);

        //var http = this.createRequestObject();
        var http = new XMLHttpRequest();

        if (http)
        {
            http.open('POST', linkHtml);
            http.setRequestHeader("Content-Type", "application/json");
            http.onreadystatechange = function ()
            {
                if (http.readyState === 4)
                {
                    cont.innerHTML = http.responseText;
                    checkedIsLogin();
                    reloadAtEndOfPageLoad(linkHtml);
                }
            };
            http.send(json);
        } else
        {
            document.location = linkHtml;
        }
    }

    /**
      Отправка json POST

      id - id формы
      linkHtml - путь
      json - json тело
    */
    loadPagePostJsonFetch(id, linkHtml, json) {

            if (id === "" || linkHtml === "" || json === "") return;

            var cont = document.getElementById(id);
            var response = fetch(linkHtml, {
                method: 'POST',
                headers: {'Content-Type': 'application/json'},
                body:json
            })
            .then(response => { return response.text();})
            .then(resBody =>
             {
             cont.innerHTML = resBody;
             checkedIsLogin();
             reloadAtEndOfPageLoad(linkHtml);
             });

    }

    /**
      Отправка формы и получение ответа в idRequest
      linkHtmlPage - путь
      idForm - id формы
      idRequest - id, куда загружается ответ от сервера
    */
    sendFormGetPage(linkHtmlPage,idForm,idRequest){

     if (linkHtmlPage === "" || idForm === "" || idRequest === "") return;

        var cont = document.getElementById(idRequest);

        function handlerPost(result) {
        cont.innerHTML = result;
        }

     sendPostFrom(linkHtmlPage, idForm, handlerPost)
    }
    // ajax объект
    createRequestObject()
    {
        try {
            return new XMLHttpRequest();
        } catch (e)
        {
            try {
                return new ActiveXObject('Msxml2.XMLHTTP');
            } catch (e)
            {
                try {
                    return new ActiveXObject('Microsoft.XMLHTTP');
                } catch (e) {
                    return null;
                }
            }
        }
    }
}

class Table {
    constructor(tableId) {
        this.table = document.getElementById(tableId);
        this.idRow = 1;
        this.row = null;
    }

    createRow() {
        this.row = this.table.insertRow(this.idRow++);
    }

    insertCellText(num, text) {
        this.row.insertCell(num).appendChild(document.createTextNode(text));
    }
}

function setCookieArr(name, valueArr) {
  var json = JSON.stringify(Array.from(valueArr));
  document.cookie = name + "=" + json + ";path=/;SameSite=Lax";
}

function setCookie(cname, cvalue, exdays) {
  var d = new Date();
  d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
  var expires = "expires="+d.toUTCString();
  document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
}

function getCookie(cname) {
  var name = cname + "=";
  var ca = document.cookie.split(';');
  for(var i = 0; i < ca.length; i++) {
    var c = ca[i];
    while (c.charAt(0) == ' ') {
      c = c.substring(1);
    }
    if (c.indexOf(name) == 0) {
      return c.substring(name.length, c.length);
    }
  }
  return "";
}

// загрузка страницы
function getHtmlContent(id,page) {
 new Page().loadPage(id,page);
}

function sendJsonGetContent(id,page,json) {
 //new Page().loadPagePostJson(id,page,json);
 new Page().loadPagePostJsonFetch(id,page,json);
}