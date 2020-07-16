console.log("status-tool.js")

$(document).ready(function() {
    close($('#interested'));
    open($('#learning'));
    close($('#hobbyist'));
});

$('#interested').click(function () {
    open($('#interested'))
    close($('#learning'))
    close($('#hobbyist'));
});

$('#learning').click(function () {
    close($('#interested'))
    open($('#learning'))
    close($('#hobbyist'))
});

$('#hobbyist').click(function () {
    close($('#interested'))
    close($('#learning'))
    open($('#hobbyist'))
});

function open(status) {
    status.addClass('s10');
    status.removeClass('s1');
    status.addClass('lighten-5');
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
    status.removeClass('lighten-5');
    status.addClass('ease');
    status.children('.content').hide();
    status.children('.title').addClass('rotate');
}