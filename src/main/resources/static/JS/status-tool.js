console.log("status-tool.js")

$(document).ready(function() {
    close($('#interested'));
    open($('#learning'));
    close($('#hobbyist'));
    $('#learning h5').addClass('blue-grey-text text-darken-4');
});

$('#interested').click(function () {
    open($('#interested'))
    close($('#learning'))
    close($('#hobbyist'));
    $('#interested h5').addClass('blue-grey-text text-darken-4');
    $('#hobbyist h5').removeClass('blue-grey-text text-darken-4');
    $('#learning h5').removeClass('blue-grey-text text-darken-4');
});

$('#learning').click(function () {
    close($('#interested'))
    open($('#learning'))
    close($('#hobbyist'))
    $('#learning h5').addClass('blue-grey-text text-darken-4');
    $('#hobbyist h5').removeClass('blue-grey-text text-darken-4');
    $('#interested h5').removeClass('blue-grey-text text-darken-4');
});

$('#hobbyist').click(function () {
    close($('#interested'))
    close($('#learning'))
    open($('#hobbyist'))
    $('#hobbyist h5').addClass('blue-grey-text text-darken-4');
    $('#interested h5').removeClass('blue-grey-text text-darken-4');
    $('#learning h5').removeClass('blue-grey-text text-darken-4');
});

function open(status) {
    status.addClass('s10');
    status.removeClass('s1');
    status.addClass('back');
    status.removeClass('darken-4');
    status.addClass('ease');
    status.children('.content').show();
    status.children('.title').show();
    status.children('.title').removeClass('rotate');
}

function close(status) {
    status.addClass('s1');
    status.removeClass('s10');
    status.addClass('darken-4');
    status.removeClass('back');
    status.addClass('ease');
    status.children('.content').hide();
    status.children('.title').addClass('rotate');
}