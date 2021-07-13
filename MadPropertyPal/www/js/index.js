$(document).on("ready", function(){
    databaseHandler.createDatabase();
});
function addproperty(){
    var PropertyName = $("#txtName").val();
    var propertyType = $("#txtType").val();
    var LeaseType = $("#txtLease").val();
    var Location = $("#txtLocation").val();
    var NumberofBedrooms = $("#txtBedrooms").val();
    var NumberofBathrooms = $("#txtBathrooms").val();
    var Size = $("#txtSize").val();
    var AskingPrice = $("#txtPrice").val();
    var LocalAmenities = $("#txtAmenities").val();
    var Description = $("#txtDescription").val();
    

    if(!PropertyName || !propertyType || !LeaseType || !Location || !NumberofBedrooms || !NumberofBathrooms || !Size || !AskingPrice){
        alert("Missing required Fields");
    }else{
        var r = confirm("PropertyName: " + PropertyName + "\n" + "propertyType: " + propertyType  + "\n" + "Lease Type: " + LeaseType + "\n" + "Location: " + Location + "\n" + "Number of Bedrooms: " + NumberofBedrooms + "\n"  + "Number of Bathrooms: "+ NumberofBathrooms + "\n" + "Size: " + Size + "\n"  + "Asking Price: "+ AskingPrice + "\n" + "Local Amenities: " + LocalAmenities + "\n"  + "Description: "+ Description);
        if(r==true){
            propertyHandler.addproperty(PropertyName, propertyType,LeaseType,Location,NumberofBedrooms,NumberofBathrooms,Size,AskingPrice,LocalAmenities,Description);
            $("#txtName").val("");
            $("#txtType").val("");
            $("#txtLease").val("");
            $("#txtLocation").val("");
            $("#txtBedrooms").val("");
            $("#txtBathrooms").val("");
            $("#txtSize").val("");
            $("#txtPrice").val("");
            $("#txtAmenities").val("");
            $("#txtDescription").val("");
        }
    }
}
var currentproperty={
id: -1,
PropertyName:"",
propertyType:"",
LeaseType:"",
Location:"",
NumberofBedrooms:-1,
NumberofBathrooms:-1,
Size:-1,
AskingPrice:-1,
LocalAmenities:"",
Description:"",
}
function displayproperty(results){
    var length = results.rows.length;
    var lstproperty = $("#lstproperty");
    lstproperty.empty();//Clean the old data before adding.
    for(var i = 0; i< length; i++){
        var item = results.rows.item(i);
        var a = $("<a />");
        var h3 = $("<h3 />").text("property name:       ");
        var h41 = $("<h4 />").text("property Type:       ");
        var h42 = $("<h4 />").text("Lease Type:          ");
        var h43 = $("<h4 />").text("Location:            ");
        var h44 = $("<h4 />").text("Number of Bedrooms:  ");
        var h45 = $("<h4 />").text("Number of Bathrooms: ");
        var h46 = $("<h4 />").text("Size:                ");
        var h47 = $("<h4 />").text("Asking Price:        ");
        var h48 = $("<h4 />").text("LocalAmenities:      ");
        var h49 = $("<h4 />").text("Description:         ");
        var p = $("<p />").text("Id:                    ");
        var spanName = $("<span />").text(item.PropertyName);
        spanName.attr("name", "PropertyName");
        var spandtype = $("<span />").text(item.propertyType);
        spandtype.attr("name", "propertyType");
        var spandlease = $("<span />").text(item.LeaseType);
        spandlease.attr("name", "LeaseType");
        var spandlocation = $("<span />").text(item.Location);
        spandlocation.attr("name", "Location");
        var spandbedrooms = $("<span />").text(item.NumberofBedrooms);
        spandbedrooms.attr("name", "NumberofBedrooms");
        var spandbathrooms = $("<span />").text(item.NumberofBathrooms);
        spandbathrooms.attr("name", "NumberofBathrooms");
        var spandSize = $("<span />").text(item.Size);
        spandSize.attr("name", "Size");
        var spandPrice = $("<span />").text(item.AskingPrice);
        spandPrice.attr("name", "AskingPrice");
        var spandAmenities = $("<span />").text(item.LocalAmenities);
        spandAmenities.attr("name", "LocalAmenities");
        var spandDesc = $("<span />").text(item.Description);
        spandDesc.attr("name", "Description");
        
        var spanId = $("<span />").text(item._id);
        spanId.attr("name", "_id");
        h3.append(spanName);
        h41.append(spandtype);
        h42.append(spandlease);
        h43.append(spandlocation);
        h44.append(spandbedrooms);
        h45.append(spandbathrooms);
        h46.append(spandSize);
        h47.append(spandPrice);
        h48.append(spandAmenities);
        h49.append(spandDesc);
        p.append(spanId);
        a.append(h3);
        a.append(h41);
        a.append(h42);
        a.append(h43);
        a.append(h44);
        a.append(h45);
        a.append(h46);
        a.append(h47);
        a.append(h48);
        a.append(h49);
        a.append(p);
        var li = $("<li/>");
        li.attr("data-filtertext", item.PropertyName);
        li.append(a);
        lstproperty.append(li);
    }

    lstproperty.listview("refresh");
    lstproperty.on("tap", "li", function(){
        currentproperty.id = $(this).find("[name='_id']").text();
        currentproperty.PropertyName = $(this).find("[name='PropertyName']").text();
        currentproperty.propertyType = $(this).find("[name='propertyType']").text();
        //Set event for the list item
        $("#popupUpdateDelete").popup("open");
    });
}

$(document).on("pagebeforeshow", "#loadpage", function(){
    propertyHandler.loadproperty(displayproperty);
});



function deleteproperty(){
    var r = confirm("Delete property\nName: "+currentproperty.PropertyName+
                    "\n Property Type: " + currentproperty.propertyType+
                    "\n Id: " + currentproperty.id);
    if(r==true){
        propertyHandler.deleteproperty(currentproperty.id);
        propertyHandler.loadproperty(displayproperty);
    }
    $("#popupUpdateDelete").popup("close");
}

$(document).on("pagebeforeshow", "#updatedialog", function(){
    $("#txtNewName").val(currentproperty.name);
    $("#txtNewQuantity").val(currentproperty.quantity);
});

function updateproperty(){
    var PropertyName = $("#txtNewName").val();
    var propertyType = $("#txtNewType").val();
    var LeaseType = $("#txtNewLease").val();
    var Location = $("#txtNewLocation").val();
    var NumberofBedrooms = $("#txtNewBedrooms").val();
    var NumberofBathrooms = $("#txtNewBathrooms").val();
    var Size = $("#txtSize").val();
    var AskingPrice = $("#txtNewPrice").val();
    var LocalAmenities = $("#txtNewAmenities").val();
    var Description = $("#txtNewDescription").val();
    propertyHandler.updateproperty(currentproperty.id, PropertyName, propertyType,LeaseType,Location,NumberofBedrooms,NumberofBathrooms,Size,AskingPrice,LocalAmenities,Description);
    $("#updatedialog").dialog("close");
}