package com.example.fooddeliv

class ModelListCanteen {

    var Image: String? = null
    var Price: String? = null
    var Title: String? = null


    constructor():this("","", ""){

    }

    constructor(Image: String?, Price: String?, Title: String?) {
        this.Image = Image
        this.Price = Price
        this.Title = Title

    }


}
