async function deleteComment(id) {
    let url = "/comment/" + id;
    await fetch(url, {
        method: 'GET'
    })
    window.location.reload()
}