console.log("hello from JS!");

$('form').submit(function (event) {
    const noEmptyFields = () => {
        //if name input text field is blank
        //jquery $.trim() used to remove whitespace to in case of incorrect validation error
        if ($.trim($('#name').val()) === '') {
            //prevent form submission and add error classes/msg
            event.preventDefault();
            $('.name-error').remove();
            $('#name').addClass('error');
            $('#name').before('<p class=name-error>Please enter a name.</p>');
            $('p').addClass('error-text');
            $('html,body').scrollTop(0);
            //otherwise remove error classes allow form submission
        } else {
            $('p').remove('.name-error');
            $('#name').removeClass('error');
        }

        if ($.trim($('#dob').val()) === '') {
            //prevent form submission and add error classes/msg
            event.preventDefault();
            $('.dob-error').remove();
            $('#dob').addClass('error');
            $('#dob').before('<p class=dob-error>Please enter a date of birth.</p>');
            $('p').addClass('error-text');
            $('html,body').scrollTop(0);
            //otherwise remove error classes allow form submission
        } else {
            $('p').remove('.dob-error');
            $('#dob').removeClass('error');
        }

        if ($.trim($('#sex').val()) === '') {
            //prevent form submission and add error classes/msg
            event.preventDefault();
            $('.sex-error').remove();
            $('#sex').addClass('error');
            $('#sex').before('<p class=sex-error>Please enter patient\'s sex.</p>');
            $('p').addClass('error-text');
            $('html,body').scrollTop(0);
            //otherwise remove error classes allow form submission
        } else {
            $('p').remove('.sex-error');
            $('#sex').removeClass('error');
        }

        if ($.trim($('#phone').val()) === '') {
            //prevent form submission and add error classes/msg
            event.preventDefault();
            $('.phone-error').remove();
            $('#phone').addClass('error');
            $('#phone').before('<p class=phone-error>Please enter a phone number.</p>');
            $('p').addClass('error-text');
            $('html,body').scrollTop(0);
            //otherwise remove error classes allow form submission
        } else {
            $('p').remove('.phone-error');
            $('#phone').removeClass('error');
        }

        if ($.trim($('#address').val()) === '') {
            //prevent form submission and add error classes/msg
            event.preventDefault();
            $('.address-error').remove();
            $('#address').addClass('error');
            $('#address').before('<p class=address-error>Please enter an address.</p>');
            $('p').addClass('error-text');
            $('html,body').scrollTop(0);
            //otherwise remove error classes allow form submission
        } else {
            $('p').remove('.address-error');
            $('#address').removeClass('error');
        }

        if ($.trim($('#city').val()) === '') {
            //prevent form submission and add error classes/msg
            event.preventDefault();
            $('.city-error').remove();
            $('#city').addClass('error');
            $('#city').before('<p class=city-error>Please enter a city.</p>');
            $('p').addClass('error-text');
            $('html,body').scrollTop(0);
            //otherwise remove error classes allow form submission
        } else {
            $('p').remove('.city-error');
            $('#city').removeClass('error');
        }

        if ($.trim($('#state').val()) === '') {
            //prevent form submission and add error classes/msg
            event.preventDefault();
            $('.state-error').remove();
            $('#state').addClass('error');
            $('#state').before('<p class=state-error>Please enter a state .</p>');
            $('p').addClass('error-text');
            $('html,body').scrollTop(0);
            //otherwise remove error classes allow form submission
        } else {
            $('p').remove('.state-error');
            $('#state').removeClass('error');
        }

        if ($.trim($('#zip').val()) === '') {
            //prevent form submission and add error classes/msg
            event.preventDefault();
            $('.zip-error').remove();
            $('#zip').addClass('error');
            $('#zip').before('<p class=zip-error>Please enter a zip code.</p>');
            $('p').addClass('error-text');
            $('html,body').scrollTop(0);
            //otherwise remove error classes allow form submission
        } else {
            $('p').remove('.zip-error');
            $('#zip').removeClass('error');
        }

        if ($.trim($('#drugName').val()) === '') {
            //prevent form submission and add error classes/msg
            event.preventDefault();
            $('.drugName-error').remove();
            $('#drugName').addClass('error');
            $('#drugName').before('<p class=drugName-error>Please enter a drug name.</p>');
            $('p').addClass('error-text');
            $('html,body').scrollTop(0);
            //otherwise remove error classes allow form submission
        } else {
            $('p').remove('.drugName-error');
            $('#drugName').removeClass('error');
        }

        if ($.trim($('#drugForm').val()) === '') {
            //prevent form submission and add error classes/msg
            event.preventDefault();
            $('.drugForm-error').remove();
            $('#drugForm').addClass('error');
            $('#drugForm').before('<p class=drugForm-error>Please enter a drug form.</p>');
            $('p').addClass('error-text');
            $('html,body').scrollTop(0);
            //otherwise remove error classes allow form submission
        } else {
            $('p').remove('.drugForm-error');
            $('#drugForm').removeClass('error');
        }

        if ($.trim($('#drugStrength').val()) === '') {
            //prevent form submission and add error classes/msg
            event.preventDefault();
            $('.drugStrength-error').remove();
            $('#drugStrength').addClass('error');
            $('#drugStrength').before('<p class=drugStrength-error>Please enter a drug strength.</p>');
            $('p').addClass('error-text');
            $('html,body').scrollTop(0);
            //otherwise remove error classes allow form submission
        } else {
            $('p').remove('.drugStrength-error');
            $('#drugStrength').removeClass('error');
        }

        if ($.trim($('#drugSig').val()) === '') {
            //prevent form submission and add error classes/msg
            event.preventDefault();
            $('.drugSig-error').remove();
            $('#drugSig').addClass('error');
            $('#drugSig').before('<p class=drugSig-error>Please enter directions.</p>');
            $('p').addClass('error-text');
            $('html,body').scrollTop(0);
            //otherwise remove error classes allow form submission
        } else {
            $('p').remove('.drugSig-error');
            $('#drugSig').removeClass('error');
        }

        if ($.trim($('#drugDose').val()) === '') {
            //prevent form submission and add error classes/msg
            event.preventDefault();
            $('.drugDose-error').remove();
            $('#drugDose').addClass('error');
            $('#drugDose').before('<p class=drugDose-error>Please enter a drug dose.</p>');
            $('p').addClass('error-text');
            $('html,body').scrollTop(0);
            //otherwise remove error classes allow form submission
        } else {
            $('p').remove('.drugDose-error');
            $('#drugDose').removeClass('error');
        }

        if ($.trim($('#drugQuantity').val()) === '') {
            //prevent form submission and add error classes/msg
            event.preventDefault();
            $('.drugQuantity-error').remove();
            $('#drugQuantity').addClass('error');
            $('#drugQuantity').before('<p class=drugQuantity-error>Please enter a drug quantity.</p>');
            $('p').addClass('error-text');
            $('html,body').scrollTop(0);
            //otherwise remove error classes allow form submission
        } else {
            $('p').remove('.drugQuantity-error');
            $('#drugQuantity').removeClass('error');
        }

        if ($.trim($('#daysSupply').val()) === '') {
            //prevent form submission and add error classes/msg
            event.preventDefault();
            $('.daysSupply-error').remove();
            $('#daysSupply').addClass('error');
            $('#daysSupply').before('<p class=daysSupply-error>Please enter a days supply.</p>');
            $('p').addClass('error-text');
            $('html,body').scrollTop(0);
            //otherwise remove error classes allow form submission
        } else {
            $('p').remove('.daysSupply-error');
            $('#daysSupply').removeClass('error');
        }

        if ($.trim($('#drugRefills').val()) === '') {
            //prevent form submission and add error classes/msg
            event.preventDefault();
            $('.drugRefills-error').remove();
            $('#drugRefills').addClass('error');
            $('#drugRefills').before('<p class=drugRefills-error>Please enter a drug refills.</p>');
            $('p').addClass('error-text');
            $('html,body').scrollTop(0);
            //otherwise remove error classes allow form submission
        } else {
            $('p').remove('.drugRefills-error');
            $('#drugRefills').removeClass('error');
        }

        if ($.trim($('#prescriberName').val()) === '') {
            //prevent form submission and add error classes/msg
            event.preventDefault();
            $('.prescriberName-error').remove();
            $('#prescriberName').addClass('error');
            $('#prescriberName').before('<p class=prescriberName-error>Please enter a prescriber name.</p>');
            $('p').addClass('error-text');
            $('html,body').scrollTop(0);
            //otherwise remove error classes allow form submission
        } else {
            $('p').remove('.prescriberName-error');
            $('#prescriberName').removeClass('error');
        }

        if ($.trim($('#npi').val()) === '') {
            //prevent form submission and add error classes/msg
            event.preventDefault();
            $('.npi-error').remove();
            $('#npi').addClass('error');
            $('#npi').before('<p class=npi-error>Please enter an NPI.</p>');
            $('p').addClass('error-text');
            $('html,body').scrollTop(0);
            //otherwise remove error classes allow form submission
        } else {
            $('p').remove('.npi-error');
            $('#npi').removeClass('error');
        }

    };
    //call name validation
    noEmptyFields();

});