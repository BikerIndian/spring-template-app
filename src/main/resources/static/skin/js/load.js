
importJsNoCache("./skin/js/pages/page_index.js");

function reloadAtEndOfPageLoad(linkHtml) {

    new PageIndex().update(linkHtml);

}

function pageLoadIndex() {
   // Проверка загрузки страницы Index
    try {

    } catch (e) {
     alert('Ошибка ' + e.name + ":" + e.message + "\n" + e.stack);
      // обработка ошибки

    }

}