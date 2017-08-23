function myFunction() {
    var x = document.getElementById('helpingnow');
    if (x.style.visibility == 'none') {
        x.style.visibility = 'block';
    } else {
        x.style.visibility = 'collapse';
    }
}