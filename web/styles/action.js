function deleteContacts() {
    document.fom.action = "DeleteAction";
    document.fom.submit();
}

function edit(val) {
    document.fom.action = "EditAction?id=" + val;
    document.fom.submit();
}