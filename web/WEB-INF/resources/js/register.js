function enableSubmit() {
	var is_login = $('#login').val();
	var regLogin = /^[a-zA-Z][a-zA-Z0-9-_\.]{5,10}$/;
	var curLogin = false;
	if (regLogin.test(is_login) & $('#login').hasClass('valid')) {
		curLogin = true;
	}

	var is_pass1 = $('#pass1').val();
	var is_pass2 = $('#pass2').val();
	var curPass = false;
	if (is_pass1.length > 5 & is_pass1.length < 11 & (is_pass1 == is_pass2)) {
		curPass = true;
	}

	var is_fname = $('#fname').val();
	var regFname = /^[a-zA-Zа-яА-Я]{1,20}$/;
	var curFname = false;
	if (regFname.test(is_fname) & $('#fname').hasClass('valid')) {
		curFname = true;
	}

	var is_lname = $('#lname').val();
	var regLname = /^[a-zA-Zа-яА-Я]{1,20}$/;
	var curLname = false;
	if (regLname.test(is_lname) & $('#lname').hasClass('valid')) {
		curLname = true;
	}

	var is_email = $('#email').val();
	var regEmail = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
	var curEmail = false;
	if (regEmail.test(is_email) & $('#email').hasClass('valid')) {
		curEmail = true;
	}

	var is_account = $('#account').val();
	var regAccount = /^[\d]{5,6}$/;
	var curAccount = false;
	if (regAccount.test(is_account) & $('#account').hasClass('valid')) {
		curAccount = true;
	}

	var is_zip = $('#zip').val();
	var regZip = /^[\d]{5,6}$/;
	var curZip = false;
	if (regZip.test(is_zip) & $('#zip').hasClass('valid')) {
		curZip = true;
	}

	var is_state = $('#state').val();
	var regState = /^[a-zA-Zа-яА-Я]{1,20}$/;
	var curState = false;
	if (regState.test(is_state) & $('#state').hasClass('valid')) {
		curState = true;
	}

	var is_city = $('#city').val();
	var regCity = /^[a-zA-Zа-яА-Я]{1,20}$/;
	var curCity = false;
	if (regCity.test(is_city) & $('#state').hasClass('valid')) {
		curCity = true;
	}

	var is_street = $('#street').val();
	var curStreet = false;
	if (is_street != "" & is_street.length > 1) {
		curStreet = true;
	}

	var is_phone = $('#phone').val();
	var regPhone = /^[\d]{7,12}$/;
	var curPhone = false;
	if (regPhone.test(is_phone) & $('#phone').hasClass('valid')) {
		curPhone = true;
	}

	if (curLogin == true & curPass == true & curFname == true
			& curLname == true & curEmail == true & curAccount == true
			& curZip == true & curState == true & curCity == true
			& curStreet == true & curPhone == true) {
		$('#submitRegister').removeAttr('disabled');
		// alert('enabled');
	} else {
		$('#submitRegister').attr("disabled", "disabled");
		// alert('disabled');
	}

};

$(document)
		.ready(
				function() {
					$("input").focus(function() {
						$(this).css("background-color", "#e4e4e4");
					});
					$("input").blur(function() {
						$(this).css("background-color", "#ffffff");
					});

					$('#login').on('input', function() {
						var input = $(this);
						var is_login = input.val();
						var reg = /^[a-zA-Z][a-zA-Z0-9-_\.]{5,10}$/;
						if (reg.test(is_login)) {
							input.removeClass("invalid").addClass("valid");
						} else {
							input.removeClass("valid").addClass("invalid");
						}
						enableSubmit();
					});

					$('#pass1').on(
							'input',
							function() {
								var input1 = $(this);
								var is_pass1 = input1.val();
								var input2 = $('#pass2');
								var is_pass2 = input2.val();
								if (is_pass1.length > 5 & is_pass1.length < 11
										& (is_pass1 == is_pass2)) {
									input1.removeClass("invalid").addClass(
											"valid");
									input2.removeClass("invalid").addClass(
											"valid");
								} else {
									input1.removeClass("valid").addClass(
											"invalid");
									input2.removeClass("valid").addClass(
											"invalid");
								}
								enableSubmit();
							});

					$('#pass2').on(
							'input',
							function() {
								var input1 = $(this);
								var is_pass1 = input1.val();
								var input2 = $('#pass1');
								var is_pass2 = input2.val();
								if (is_pass1.length > 5 & is_pass1.length < 11
										& (is_pass1 == is_pass2)) {
									input1.removeClass("invalid").addClass(
											"valid");
									input2.removeClass("invalid").addClass(
											"valid");
								} else {
									input1.removeClass("valid").addClass(
											"invalid");
									input2.removeClass("valid").addClass(
											"invalid");
								}
								enableSubmit();
							});

					$('#fname').on('input', function() {
						var input = $(this);
						var is_fname = input.val();
						var reg = /^[a-zA-Zа-яА-Я]{1,20}$/;
						if (reg.test(is_fname)) {
							input.removeClass("invalid").addClass("valid");
						} else {
							input.removeClass("valid").addClass("invalid");
						}
						enableSubmit();
					});

					$('#lname').on('input', function() {
						var input = $(this);
						var is_lname = input.val();
						var reg = /^[a-zA-Zа-яА-Я]{1,20}$/;
						if (reg.test(is_lname)) {
							input.removeClass("invalid").addClass("valid");
						} else {
							input.removeClass("valid").addClass("invalid");
						}
						enableSubmit();
					});

					$('#email')
							.on(
									'input',
									function() {
										var input = $(this);
										var is_email = input.val();
										var reg = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
										if (reg.test(is_email)
												& is_email.length < 31) {
											input.removeClass("invalid")
													.addClass("valid");
										} else {
											input.removeClass("valid")
													.addClass("invalid");
										}
										enableSubmit();
									});

					$('#account').on('input', function() {
						var input = $(this);
						var is_account = input.val();
						var reg = /^[\d]{5,6}$/;
						if (reg.test(is_account)) {
							input.removeClass("invalid").addClass("valid");
						} else {
							input.removeClass("valid").addClass("invalid");
						}
						enableSubmit();
					});

					$('#zip').on('input', function() {
						var input = $(this);
						var is_zip = input.val();
						var intRegex = /^[\d]{5,6}$/;
						if (intRegex.test(is_zip)) {
							input.removeClass("invalid").addClass("valid");
						} else {
							input.removeClass("valid").addClass("invalid");
						}
						enableSubmit();
					});

					$('#state').on('input', function() {
						var input = $(this);
						var is_state = input.val();
						var reg = /^[a-zA-Zа-яА-Я]{1,20}$/;
						if (reg.test(is_state)) {
							input.removeClass("invalid").addClass("valid");
						} else {
							input.removeClass("valid").addClass("invalid");
						}
						enableSubmit();
					});

					$('#city').on('input', function() {
						var input = $(this);
						var is_city = input.val();
						var reg = /^[a-zA-Zа-яА-Я]{1,20}$/;
						if (reg.test(is_city)) {
							input.removeClass("invalid").addClass("valid");
						} else {
							input.removeClass("valid").addClass("invalid");
						}
						enableSubmit();
					});

					$('#street').on('input', function() {
						var input = $(this);
						var is_street = input.val();
						if (is_street | is_street.length > 1) {
							input.removeClass("invalid").addClass("valid");
						} else {
							input.removeClass("valid").addClass("invalid");
						}
						enableSubmit();
					});

					$('#phone').on('input', function() {
						var input = $(this);
						var is_phone = input.val();
						var reg = /^[\d]{7,12}$/;
						if (reg.test(is_phone)) {
							input.removeClass("invalid").addClass("valid");
						} else {
							input.removeClass("valid").addClass("invalid");
						}
						enableSubmit();
					});

				});