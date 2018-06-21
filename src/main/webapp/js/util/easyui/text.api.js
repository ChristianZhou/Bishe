/**
 * 
 */

$.extend($.fn.validatebox.defaults.rules, {
    ident: {
		validator: function(value,param){
			return /^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$/.test(value);
		},
		message: "请输入正确的身份证号"
    }
});


$('#cc').combobox({
    url:'combobox_data.json',
    valueField:'id',
    textField:'text'
});