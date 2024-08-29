export function serializeUser(form) {
    let user = {};
    user["authorities"] = [];
    $.each(form.serializeArray(), (i, field) => {
        if (field.name === "roles") {
            user["authorities"].push(field.value);
        } else {
            user[field.name] = field.value;
        }
    });
    $.each(user.authorities, (key, value) => {
        user.authorities[key] = roles.find(x => x["authority"] === value);
    });
    return user;
}

export function switchPanel(hideSelector, showSelector, deactivateSelector, activateSelector) {
    $(hideSelector).hide();
    $(showSelector).show();
    $(deactivateSelector).removeClass("active");
    $(activateSelector).addClass("active");
}