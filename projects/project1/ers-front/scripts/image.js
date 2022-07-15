async function getAsByteArray(file) {
    return new Uint8Array(await readFile(file));
}

function readFile(file) {
    return new Promise((resolve, reject) => {
        // Create file reader
        let reader = new FileReader();
        // Register event listeners
        reader.addEventListener("loadend", e => resolve(e.target.result));
        reader.addEventListener("error", reject);
        // Read file
        reader.readAsArrayBuffer(file);
    })
}

function getImage() {
    let file = document.getElementById('newReceipt').files[0];
    let blob = getAsByteArray(file);
    return blob;
}

function showImage() {
    var img = document.getElementById('viewReceipt');
    var reader = new FileReader();
    reader.addEventListener("load", function () {
        var theBlob = reader.result;
        img.src = theBlob;
    }, false);
}