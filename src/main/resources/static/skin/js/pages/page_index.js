class PageIndex{

    constructor() {
       this.cookieName = "addCargoArr";
       this.idCargoCounter = "cargoCounter";
    }

    update(linkHtml){
     this.updateAddCargoIcon();
    }

    // Обновить счетчик грузов для отгрузки
    updateAddCargoIcon() {
      var cargoCounter = document.getElementById(this.idCargoCounter);
      cargoCounter.innerText = this.getSizeCookieCargoArr();
    }

    addCargoNumberToCookie(cargoNum){
      var cargoSet = this.getCookieCargoArr();
      cargoSet.add(cargoNum);
      setCookieArr(this.cookieName,cargoSet);
    }

    clearCookieCargoArr(){
      setCookieArr(this.cookieName,new Set());
    }

    deleteCargoNumberToCookie(cargoNum){
      var cargoSet = this.getCookieCargoArr();
      cargoSet.delete(cargoNum);
      setCookieArr(this.cookieName,cargoSet);
    }

    getSizeCookieCargoArr(){
      return this.getCookieCargoArr().size;
    }

    // получить массив грузов из cookie
    getCookieCargoArr(){
      var cookieCargos = getCookie(this.cookieName);
      var result = new Set();

      if (cookieCargos !== ""){
         result = new Set(JSON.parse(cookieCargos));
      }

      return result;
    }

    // переход на окно создания заявки на отгрузку
    btnClickCreateCargosShipping() {
      var url = '/pages/cargos-shipping/create';
      var cookieCargos = getCookie(this.cookieName);

      if (cookieCargos !== "" & cookieCargos !== "[]" ){
        console.log(cookieCargos);
        var cargos = {listCargos: JSON.parse(cookieCargos)};
        var json = JSON.stringify(cargos);
        sendJsonGetContent("row-Page",url,json);
      }
    }

    loadingPageLogin(){
     this.clearCookieCargoArr();
    }
}

function btnClickCreateCargosShipping() {
    new PageIndex().btnClickCreateCargosShipping();
}