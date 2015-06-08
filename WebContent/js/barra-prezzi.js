$(document).ready(function() {
        $("#slider").slider({
            animate: true,
            value:1,
            min: 0,
            max: 1000,
            step: 10,
            slide: function(event, ui) {
                update(1,ui.value); //changed
            }
        });

        //Added, set initial value.
        $("#amount").val(0);
        $("#amount-label").text(0);
        
        update();
    });

    //changed. now with parameter
    function update(slider,val) {
      //changed. Now, directly take value from ui.value. if not set (initial, will use current value.)
      var $amount = slider == 1?val:$("#amount").val();

      /* commented
      $amount = $( "#slider" ).slider( "value" );
      $duration = $( "#slider2" ).slider( "value" );
       */

       $('#slider a').html('<label><span class="glyphicon glyphicon-chevron-left"></span> '+$amount+' <span class="glyphicon glyphicon-chevron-right"></span></label>');
    }