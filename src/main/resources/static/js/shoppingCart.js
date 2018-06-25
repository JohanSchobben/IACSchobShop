var shoppingCartElement = $("#shopping-cart");

var shoppingCartLines = [];
var removeItem =  function (itemId){
    shoppingCartLines = shoppingCartLines.filter(function(shoppingCartLine){
        return itemId != shoppingCartLine.productId;
    })
};
var addItem = function (newItem){
    for(var i=0; i< shoppingCartLines.length; i++){
        var shoppingCartLine = shoppingCartLines[i];
        if(shoppingCartLine.productId == newItem.productId){
            shoppingCartLine.amount += newItem.amount;
            return;
        }
    }
    shoppingCartLines.push(newItem);
};
var updateItem = function(productId, newAmount){
    for(var i=0; i< shoppingCartLines.length; i++){
        var shoppingCartLine = shoppingCartLines[i];
        if(shoppingCartLine.productId == productId){
            shoppingCartLine.amount = newAmount;
        }
    }
};
var getTotalPrice = function(){
    var price = 0;
    for(var i=0; i< shoppingCartLines.length; i++){
        var shoppingCartLine = shoppingCartLines[i];
        price += (shoppingCartLine.amount * shoppingCartLine.price)
    }
    return price;
};
var shoppingCartLineObject = function (id,name,price, amount){
    return {
        amount: amount,
        productId: id,
        productName: name,
        price: price
    }
};
var onRemoveClick = function (productId){
    removeItem(productId);
    UpdateState()
};

var onAmountChange = function(productId){
    updateItem(productId,$("#cart-item-"+ productId).val());
    shoppingCartElement.html(generateShoppingCartLineHTML());
    updateShoppingCart();
};

var generateShoppingCartLineHTML = function(){
    var html = '<div class="row"><div class="col"><span class="font-weight-bold">Shopping cart</span></div></div>';
    if(shoppingCartLines && shoppingCartLines.length === 0){
        return html + '<div class="row"><div class="col">no items in shopping cart</div></div>';
    }
    for(var i=0; i< shoppingCartLines.length; i++) {
        var shoppingCartLine = shoppingCartLines[i];
        html += '<div class="form-group row cart-line">\n' +
            '    <label for="cart-item-'+ shoppingCartLine.productId +'" class="col-sm-6">'+shoppingCartLine.productName+'</label>\n' +
            '    <span class="col-md-2">€'+(shoppingCartLine.price * shoppingCartLine.amount)+'</span>'+
            '    <div class="col-sm-4">\n' +
            '      <input type="number" onchange="onAmountChange('+shoppingCartLine.productId+')" min="1" max="9" class="form-control form-control-sm shopping-cart__ammount" id="cart-item-'+ shoppingCartLine.productId +'" value="'+shoppingCartLine.amount+'">\n' +
            '          <small onClick="onRemoveClick('+shoppingCartLine.productId+')" class="form-text text-info cart-line__delete">remove</small>\n' +
            '</div>\n' +
            '  </div>'
    }
    html += '<div class="row"><div class="col">Total:</div><div class="col">'+ getTotalPrice() + '</div> </div>'
    html += '<div class="row"><div class="col-6"><a href="/order" class="btn btn-primary">Buy items</a></div> </div>'
    return html;
};

var getShoppingCart = function (){
    $.ajax('/shoppingcart/get',{
        headers:{
            Accept: "application/json;",
        },
        success: function(responseData){
            shoppingCartLines = responseData.items || [];
            shoppingCartElement.html(generateShoppingCartLineHTML());
        }
    });
};

var updateShoppingCart = function(){
    $.ajax('/shoppingcart/update',{
        method: 'POST',
        contentType: 'application/json; charset=utf-8',
        data:JSON.stringify({
            items: shoppingCartLines
        }),
        success: function(responseData){
            console.log(responseData)
        }
    });
};

//eventListeners

$("#order-button").click(function(){
    var orderInfoElement = $('#product-info');
    var id = orderInfoElement.val();
    var name  = orderInfoElement.data("name");
    var price = Number(orderInfoElement.data("price")) ;
    var amount = Number($('#order-amount').val());
    addItem(shoppingCartLineObject(id,name,price,amount));
    UpdateState()
});
UpdateState = function(){
    shoppingCartElement.html(generateShoppingCartLineHTML());
    updateShoppingCart();

}

getShoppingCart();
