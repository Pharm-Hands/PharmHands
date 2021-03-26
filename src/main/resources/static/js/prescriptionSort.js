//NAME HEADING asc-desc toggle
('body').on('click', '.heading-rx-drug-name', function() {
    var sortArr=[];
    var prescriptions = $(this).parent().parent().parent().children().children();
    if ($('.heading-rx-drug-name').hasClass('toggle-on')) {
        $(prescriptions).each((index, value) => {
            if (index !== 0) sortArr.push(value);
        });
        sortArr.sort((a, b) => b.children[0].innerText.localeCompare(a.children[0].innerText));
        sortArr.forEach((e)=> $('thead').next().append(e));
        $('.heading-rx-drug-name').removeClass('toggle-on');
        return;
    }
    $('.heading-rx-drug-name').addClass('toggle-on');
    $(prescriptions).each((index, value) => {
        if (index !== 0) sortArr.push(value);
    });
    sortArr.sort((a, b) => a.children[0].innerText.localeCompare(b.children[0].innerText));
    sortArr.forEach((e)=> $('thead').next().append(e));
});

//