var databaseHandler = {
db: null,
createDatabase: function(){
    this.db = window.openDatabase(
        "property.db",
        "1.0",
        "property database",
        1000000);
    this.db.transaction(
        function(tx){
            //Run sql here using tx
            tx.executeSql(
                "create table if not exists property(_id integer primary key, PropertyName text, propertyType text, LeaseType text, Location text, NumberofBedrooms integer, NumberofBathrooms integer, Size integer, AskingPrice integer, LocalAmenities text, Description text)",
                [],
                function(tx, results){},
                function(tx, error){
                    console.log("Error while creating the table: " + error.message);
                }
            );
        },
        function(error){
            console.log("Transaction error: " + error.message);
        },
        function(){
            console.log("Create DB transaction completed successfuly");
        }
    );

}
}