function removeOrder(orderId) {
    let status = confirm('Are you sure?');
    if (status) {
        location.href='/orders/remove/' + orderId;
        document.getElementById('orderBox' + orderId).remove();
    }
}