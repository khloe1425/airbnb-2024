export const removeAccents = (str: string): string => {
    const accentMap: { [key: string]: RegExp } = {
        a: /á|à|ạ|ả|ã|ạ|ă|ắ|ằ|ẳ|ẵ|ặ|ấ|ầ|ẩ|ẫ|ậ/g,
        e: /é|è|ẹ|ẻ|ẽ|ệ|ê|ế|ề|ệ|ể|ễ/g,
        i: /í|ì|ị|ỉ|ĩ|ị/g,
        o: /ó|ò|ọ|ỏ|õ|ố|ồ|ộ|ổ|ỗ|ơ|ớ|ờ|ợ|ở|ỡ/g,
        u: /ú|ù|ụ|ủ|ũ|ụ|ư|ứ|ừ|ự|ử|ữ/g,
        y: /ý|ỳ|ỵ|ỷ|ỹ/g,
        d: /đ/g,
        A: /Á|À|Ạ|Ả|Ã|Ạ|Ă|Ắ|Ằ|Ẳ|Ẵ|Ặ|Ấ|Ầ|Ẩ|Ẫ|Ậ/g,
        E: /É|È|Ẹ|Ẻ|Ẽ|Ệ|Ê|Ế|Ề|Ệ|Ể|Ễ/g,
        I: /Í|Ì|Ị|Ỉ|Ĩ|Ị/g,
        O: /Ó|Ò|Ọ|Ỏ|Õ|Ố|Ồ|Ộ|Ổ|Ỗ|Ơ|Ớ|Ờ|Ợ|Ở|Ỡ/g,
        U: /Ú|Ù|Ụ|Ủ|Ũ|Ụ|Ư|Ứ|Ừ|Ự|Ử|Ữ/g,
        Y: /Ý|Ỳ|Ỵ|Ỷ|Ỹ/g,
        D: /Đ/g,
    };

    for (const letter in accentMap) {
        str = str.replace(accentMap[letter as keyof typeof accentMap], letter);
    }

    return str;
};