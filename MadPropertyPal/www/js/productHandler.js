var propertyHandler={
addproperty: function(PropertyName, propertyType,LeaseType,Location,NumberofBedrooms,NumberofBathrooms,Size,AskingPrice,LocalAmenities,Description){
    databaseHandler.db.transaction(
        function(tx){
            tx.executeSql(
                "insert into property(PropertyName, propertyType, LeaseType, Location, NumberofBedrooms, NumberofBathrooms, Size, AskingPrice, LocalAmenities, Description) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                [PropertyName, propertyType,LeaseType,Location,NumberofBedrooms,NumberofBathrooms,Size,AskingPrice,LocalAmenities,Description],
                function(tx, results){},
                function(tx, error){
                    console.log("add property error: " + error.message);
                }
            );
        },
        function(error){},
        function(){}
    );
},
loadproperty: function(displayproperty){
    databaseHandler.db.readTransaction(
        function(tx){
            tx.executeSql(
                "select * from property",
                [],
                function(tx, results){
                    //Do the display
                    displayproperty(results);
                },
                function(tx, error){//TODO: Alert the message to user
                    console.log("Error while selecting the property" + error.message);
                }
            );
        }
    );
},
deleteproperty:function(_id){
    databaseHandler.db.transaction(
        function(tx){
            tx.executeSql(
                "delete from property where _id = ?",
                [_id],
                function(tx, results){},
                function(tx, error){//TODO: Could make an alert for this one.
                    console.log("Error happen when deleting: " + error.message);
                }
            );
        }
    );
},
updateproperty: function(_id, PropertyName, propertyType,LeaseType,Location,NumberofBedrooms,NumberofBathrooms,Size,AskingPrice,LocalAmenities,Description){
    databaseHandler.db.transaction(
        function(tx){
            tx.executeSql(
                "update property set PropertyName=?, propertyType=?, LeaseType=?, Location=?, NumberofBedrooms=?, NumberofBathrooms=?, Size=?, AskingPrice=?, LocalAmenities=?, Description=? where _id = ?",
                [PropertyName, propertyType,LeaseType,Location,NumberofBedrooms,NumberofBathrooms,Size,AskingPrice,LocalAmenities,Description, _id],
                function(tx, result){},
                function(tx, error){//TODO: alert/display this message to user
                    console.log("Error updating property" + error.message);
                }
            );
        }
    );
}
};