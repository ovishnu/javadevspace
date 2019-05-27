/*---------------------form Validation to Add User----------------------*/

 $('#addUserform').formValidation({
        framework: 'bootstrap',
        excluded: ':disabled',
        icon: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
        	add_user_name: {
                validators: {
                    notEmpty: {
                        message: 'The username is required'
                    }
                }
            },
            add_user_last_name: {
                validators: {
                    notEmpty: {
                        message: 'The user lastname is required'
                    }
                }
            },
            add_user_email: {
                validators: {
                    notEmpty: {
                        message: 'The user email is required'
                    },
                    emailAddress: {
                        message: 'The input is not a valid email address'
                    } 
                }
            },
            add_user_password: {
                validators: {
                    notEmpty: {
                        message: 'The password is required'
                    }
                }
            }
        }
    }).on('success.form.fv', function(e) {
    	
    	e.preventDefault();
    	
    	var name = $("#add_user_name").val();
		var lastName = $("#add_user_last_name").val();
		var email = $("#add_user_email").val();
		var password = $("#add_user_password").val();
		
		var jsondata = {};
		jsondata["name"]= name;
		jsondata["lastName"]= lastName;
		jsondata["email"]= email;
		jsondata["password"]= password;
				
		 var userAddUrl = baseUrl + "/user/add";
		callAjaxPostJSON(userAddUrl, "POST", "", jsondata);
		 $("#addUser").modal('hide');
		 location.reload(true);
    });
  
 /*---------------------form Validation to Add User----------------------*/

 
 /*---------------------form Validation to Add Doctor----------------------*/

 $('#addDoctorform').formValidation({
     framework: 'bootstrap',
     excluded: ':disabled',
     icon: {
         valid: 'glyphicon glyphicon-ok',
         invalid: 'glyphicon glyphicon-remove',
         validating: 'glyphicon glyphicon-refresh'
     },
     fields: {
    	 add_doctor_name: {
             validators: {
                 notEmpty: {
                     message: 'The doctor name is required'
                 },
                 /*stringLength: {
                     min: 6,
                     max: 30,
                     message: 'The username must be more than 6 and less than 30 characters long'
                 },*/
                 regexp: {
                     regexp: /^[a-zA-Z0-9_\.]+$/,
                     message: 'The username can only consist of alphabetical, number, dot and underscore'
                 }
             }
         },
         add_doctor_contactNo: {
             validators: {
                 notEmpty: {
                     message: 'The contact No. is required'
                 },
                 regexp: {
                     message: 'The phone number can only contain the digits, spaces, -, (, ), + and .',
                     regexp: /^[0-9\s\-()+\.]+$/
                 }
             }
         },
         add_doctor_email: {
             validators: {
                 notEmpty: {
                     message: 'The email is required'
                 },
                 emailAddress: {
                     message: 'The input is not a valid email address'
                 }
             }
         },
         add_doctor_qualification: {
             validators: {
                 notEmpty: {
                     message: 'The qualification is required'
                 }
             }
         },
         add_doctor_speciality: {
             validators: {
                 notEmpty: {
                     message: 'The doctor speciality is required'
                 }
             }
         },
         add_doctor_hospitalName: {
             validators: {
                 notEmpty: {
                     message: 'The hospital name is required'
                 }
             }
         },
         add_doctor_address: {
             validators: {
                 notEmpty: {
                     message: 'The doctor address is required'
                 }
             }
         },
         add_doctor_commission: {
             validators: {
                 notEmpty: {
                     message: 'The doctor commission is required'
                 },
                 regexp: {
                     message: 'The commission can only contain the digits, spaces, -, (, ), + and .',
                     regexp: /^[0-9\s\-()+\.]+$/
                 }
             }
         }
     }
 }).on('success.form.fv', function(e) {
 	
 	e.preventDefault();
 	
 	var name = $(".add_doctor_name").val();				
	
	var contactNo =	$(".add_doctor_contactNo").val();
	var email =	$(".add_doctor_email").val();
	var hospitalName = $(".add_doctor_hospitalName").val();
	var qualification = $(".add_doctor_qualification").val();
	var speciality = $(".add_doctor_speciality").val();
	var address =	$(".add_doctor_address").val();
	var commission = $(".add_doctor_commission").val();

	var jsondata = {};
	jsondata["name"] = name;
	jsondata["contactNo"] = contactNo;
	jsondata["email"] = email;
	jsondata["hospitalName"] = hospitalName;
	jsondata["qualification"] = qualification;
	jsondata["speciality"] = speciality;
	jsondata["address"] = address;			
	jsondata["commission"] = commission;
	jsondata["addedByUserId"] = 1;

	/* var urlPerfix = "http://localhost:9900";
	url = urlPerfix + "/doctor/add"; */
	
	var doctorAddUrl = baseUrl + "/doctor/add";
	alert(JSON.stringify(jsondata));
	callAjaxPostJSON(doctorAddUrl, "POST", "", jsondata);

		 $("#addDoctor").modal('hide');
		 location.reload(true);
 });

/*---------------------form Validation to Add doctor----------------------*/

 /*---------------------form Validation to Add service----------------------*/

 $('#addServiceform').formValidation({
        framework: 'bootstrap',
        excluded: ':disabled',
        icon: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
        	add_new_serviceType: {
                validators: {
                    notEmpty: {
                        message: 'The Service name is required'
                    }
                }
            },
            add_new_serviceType_price: {
                validators: {
                    notEmpty: {
                        message: 'The Service price is required'
                    },
                    regexp: {
                        message: 'The commission can only contain the digits, spaces, -, (, ), + and .',
                        regexp: /^[0-9\s\-()+\.]+$/
                    }
                }
            }
        }
    }).on('success.form.fv', function(e) {
    	
    	e.preventDefault();
    	
    	var name = $("#add_new_serviceType").val();
		var price = $("#add_new_serviceType_price").val();

		var jsondata = {};
		jsondata["name"] = name;
		jsondata["price"] = price;

		var serviceAddUrl = baseUrl + "/servicetype/add";

		callAjaxPostJSON(serviceAddUrl, "POST", "", jsondata);
		 $("#addUser").modal('hide');
		 location.reload(true);
    });
  
 /*---------------------form Validation to Add Service----------------------*/ 
 
 /*---------------------form Validation to Add Patient----------------------*/
 $('#addPatientform').formValidation({
     framework: 'bootstrap',
     excluded: ':disabled',
     icon: {
         valid: 'glyphicon glyphicon-ok',
         invalid: 'glyphicon glyphicon-remove',
         validating: 'glyphicon glyphicon-refresh'
     },
     fields: {
    	 add_patient_firstname: {
             validators: {
                 notEmpty: {
                     message: 'The doctor name is required'
                 },
                 regexp: {
                     regexp: /^[a-zA-Z0-9_\.]+$/,
                     message: 'The username can only consist of alphabetical, number, dot and underscore'
                 }
             }
         },
         add_patient_lastName: {
             validators: {
                 notEmpty: {
                     message: 'The doctor name is required'
                 },
                 regexp: {
                     regexp: /^[a-zA-Z0-9_\.]+$/,
                     message: 'The username can only consist of alphabetical, number, dot and underscore'
                 }
             }
         },
         add_patient_contactNo: {
             validators: {
                 notEmpty: {
                     message: 'The contact No. is required'
                 },stringLength: {
                     min: 10,
                     max: 15,
                     message: 'The username must be more than 10 and less than 15 characters long'
                 },
                 regexp: {
                     message: 'The phone number can only contain the digits, spaces, -, (, ), + and .',
                     regexp: /^[0-9\s\-()+\.]+$/
                 }
             }
         },
         add_patient_email: {
             validators: {
                 notEmpty: {
                     message: 'The email is required'
                 },
                 emailAddress: {
                     message: 'The input is not a valid email address'
                 }
             }
         },
         add_patient_gender: {
             validators: {
                 notEmpty: {
                     message: 'The gender is required'
                 }
             }
         },
         add_patient_age: {
             validators: {
                 notEmpty: {
                     message: 'The patient age is required'
                 },
                 regexp: {
                     message: 'The commission can only contain the digits, spaces, -, (, ), + and .',
                     regexp: /^[0-9\s\-()+\.]+$/
                 }
             }
         },
        
         add_patient_address: {
             validators: {
                 notEmpty: {
                     message: 'The doctor address is required'
                 }
             }
         }
         }
 }).on('success.form.fv', function(e) {
 	
 	e.preventDefault();
 	
 	var firstName = $(".add_patient_firstname").val();				
 	var lastName = $(".add_patient_lastName").val();
	var contactNo =	$(".add_patient_contactNo").val();
	var email =	$(".add_patient_email").val();
	var gender = $(".add_patient_gender").val();
	var age = $(".add_patient_age").val();
	var address =	$(".add_patient_address").val();

	var jsondata = {};
	jsondata["firstName"] = firstName;
	jsondata["lastName"] = lastName;
	jsondata["contactNo"] = contactNo;
	jsondata["email"] = email;
	jsondata["gender"] = gender;
	jsondata["age"] = age;
	jsondata["address"] = address;			
	jsondata["addedByUserId"] = 1;

	
	var doctorAddUrl = baseUrl + "/patient/add";
	alert(JSON.stringify(jsondata));
	callAjaxPostJSON(doctorAddUrl, "POST", "", jsondata);

		 $("#addPatient").modal('hide');
		 location.reload(true);
 });

/*---------------------form Validation to Add doctor----------------------*/
 
    
    $('.modal').on('shown.bs.modal', function() {
   	$('#addUserform').formValidation('resetForm', true);
	$('#addDoctorform').formValidation('resetForm', true);
   });
    
    
    $('.modal').on('hidden.bs.modal', function() {
    	$('#addUserform').formValidation('resetForm', true);
    	$('#addDoctorform').formValidation('resetForm', true);
    });






