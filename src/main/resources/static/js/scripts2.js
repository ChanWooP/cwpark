let dv;

function font_onclick() {
    if(dv != null) {
        dv.style.color = 'black';
    }

    dv = event.currentTarget;

    dv.style.color = 'blue';
}