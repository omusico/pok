
var allowedSpecialCharKeyCodes = [46,8,37,39,35,36,9];
var numberKeyCodes = [44, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105];
var commaKeyCode = [188];
var decimalKeyCode = [189,190,110];
 /**
 *ֻ����������
 */
function onlyNumbers() {
    var legalKeyCode =
        (!event.shiftKey  && !event.altKey)
            &&
        (jQuery.inArray(event.keyCode, allowedSpecialCharKeyCodes) >= 0
            ||
        jQuery.inArray(event.keyCode, numberKeyCodes) >= 0
			||
		(event.ctrlKey && (event.keyCode==67 || event.keyCode==86)));

	if (!legalKeyCode){
         //event.preventDefault();
		event.returnValue= false;
	}

}
