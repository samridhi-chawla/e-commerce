<!doctype html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>jQuery UI Slider - Range slider</title>
  
<!--  <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <link rel="stylesheet" href="/resources/demos/style.css">
  
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  -->
  <link href="../css/range_slider.css" rel="stylesheet" type="text/css"/>
  <script src="../js/range_slider.js" type="text/javascript"></script>
<!--  <script src="../js/range_slider_mob.js" type="text/javascript"></script>-->
<script src="../js/range_slider2.js" type="text/javascript"></script>
  <script>
   var minvalue,maxvalue;
    
    $( function() 
    {
      $("#bt1").click(function ()
      {
          //alert("called");
          
          $("#slider-range").slider({values: [0, 500 ]});
      });
              
      $( "#slider-range" ).slider(
      {
      range: true,
      min: 0,
      max: 500,
      values: [ 75, 300 ],
      
      slide: function( event, ui ) 
      {
        minvalue=ui.values[ 0 ];
        maxvalue=ui.values[ 1 ];
          
        $( "#amount" ).val(minvalue + " - " + maxvalue );
        
      }
      });
    
    //to show initial value
    //$( "#amount" ).val( "$" + $( "#slider-range" ).slider( "values", 0 ) + " - $" + $( "#slider-range" ).slider( "values", 1 ) );
    } );

    
    function go()
    {
       alert(minvalue+" -- "+maxvalue);    
    }
    
  </script>
</head>
<body>
 
    
<p>
  <label for="amount">Price range:</label>
  <input type="text" id="amount" readonly style="border:0; color:#f6931f; font-weight:bold;">
</p>
 
<div id="slider-range"></div>

<input type="button" value="RESET VALUES" id="bt1"  />

<input type="button" value="Show" onclick="go()" />


</body>
</html>