export default decodeJWT2Obj;

function decodeJWT2Obj(token) {
    const parts = token.split('.');
    const header = atob(parts[0]);
    const payload = atob(parts[1]);
    return {
        header: JSON.parse(header),
        payload: JSON.parse(payload)
    }
}