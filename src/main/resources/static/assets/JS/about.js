document.addEventListener('DOMContentLoaded', function () {
    const boxes = document.querySelectorAll('.containerr-boxs');

    function checkBoxes() {
        const triggerBottom = window.innerHeight / 5 * 4;

        boxes.forEach(box => {
            const boxTop = box.getBoundingClientRect().top;

            if (boxTop < triggerBottom) {
                box.classList.add('active');
            } else {
                box.classList.remove('active');
            }
        });
    }

    window.addEventListener('scroll', checkBoxes);

    // Initial check when the page loads
    checkBoxes();
});