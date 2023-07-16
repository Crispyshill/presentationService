document.addEventListener("DOMContentLoaded", function () {
    const addToCartButtons = document.querySelectorAll(".product-details button[type='submit']");

    addToCartButtons.forEach(function (button) {
        button.addEventListener("click", function (event) {
            event.preventDefault();

            const productId = button.parentElement.querySelector("input[name='productId']").value;
            const amount = button.parentElement.querySelector("input[name='quantity']").value;

            const requestData = {
                productId: productId,
                amount: parseInt(amount)
            };
            console.log("productId: " + productId);


            fetch("/addtocart?productId=" + encodeURIComponent(productId) + "&amount=" + encodeURIComponent(amount), {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(requestData)
            })
                .then(response => {
                    if (response.ok) {
                        // Redirect to the product page
                        window.location.href = "/product/" + productId;
                    } else {
                        console.error("Failed to add product to cart.");
                    }
                })
                .catch(error => {
                    console.error("Error adding product to cart:", error);
                });
        });
    });
});
