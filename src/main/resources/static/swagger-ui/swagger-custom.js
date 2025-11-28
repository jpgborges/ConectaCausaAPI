window.addEventListener("load", function () {
    if (!window.ui) {
        console.warn("Swagger UI ainda não carregado.");
        return;
    }

    const originalFetch = window.fetch;

    window.fetch = async (...args) => {
        const response = await originalFetch(...args);

        try {
            const url = String(args[0] || "");
            const method = (args[1]?.method || "GET").toUpperCase();

            // Intercepta chamada de login
            const isLogin = url.includes("/api/auth/login") && method === "POST";

            if (isLogin && response.ok) {
                const clone = response.clone();
                const json = await clone.json().catch(() => null);

                const token =
                    json?.token ||
                    json?.accessToken ||
                    json?.jwt ||
                    json?.data?.token ||
                    null;

                if (!token) {
                    console.warn("Token não encontrado no JSON do login.");
                    return response;
                }

                const bearer = "Bearer " + token;

                // salva para persistir
                localStorage.setItem("jwtToken", token);

                if (window.ui?.preauthorizeApiKey) {
                    window.ui.preauthorizeApiKey("bearerAuth", bearer);
                    console.log("Token JWT aplicado automaticamente!");
                }
            }

        } catch (e) {
            console.error("Erro no interceptor JWT:", e);
        }

        return response;
    };
});
