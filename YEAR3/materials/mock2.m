%LUP
function X = lup_decomposition(A, B)

    [m, n] = size(A);
    P = zeros(m, n);
    pivot = (1 : m)';
    
    for i = 1 : m - 1
        % Căutăm valoarea maximă și poziția ei, unde să facem pivotarea:
        [~, position] = max(abs(A(i : m, i)));
        position = position + i - 1;
        
        if i ~= position
            % Dacă pivotul nu e egal cu linia curentă, interschimbăm liniile:
            A([i, position], :) = A([position, i], :);
            pivot([i, position]) = pivot([position, i]);
        end

        % Determinăm complementul Schur:
        row = i + 1 : m;
        A(row, i) = A(row, i) / A(i, i);
        A(row, row) = A(row, row) - A(row, i) * A(i, row);
    end

    for i = 1 : m
        P(i, pivot(i)) = 1;
    end
    
    % Decupăm triunghiul de sus al matricei:
    U = triu(A);
    % Decupăm triunghiul de jos al matricei (fără diagonala principală):
    L = tril(A,-1);
    % Punem 1 pe diagonala principală
    L = L + eye(m);

    fprintf("Matricea U:");
    disp(U);
    
    % Calculăm determinantul matricei L
    fprintf("Determinantul matricei multiplicărilor: %d", det(L));
    
    Y = L \ (P * B);
    X = U \ Y; 
    
end

%Cholesky
function X = cholesky_decomposition(A, B)
    [m, ~] = size(A);
    R = zeros(m, m);

    for k = 1 : m
        for j = k + 1 : m
            A(j, j : m) = A(j, j : m) - A(k, j : m) * A(k, j) / A(k, k);
        end
        A(k, k : m) = A(k, k : m) / sqrt(A(k, k));
        R(k, :) = A(k, :);
    end

    Y = (R.') \ B;
    X = R \ Y; 
end


% Matricea A pentru n = 10
A = [...
    3, -1, 0, 0, 0, 0, 0, 0, 0, 0;
    -1, 3, -1, 0, 0, 0, 0, 0, 0, 0;
    0, -1, 3, -1, 0, 0, 0, 0, 0, 0;
    0, 0, -1, 3, -1, 0, 0, 0, 0, 0;
    0, 0, 0, -1, 3, -1, 0, 0, 0, 0;
    0, 0, 0, 0, -1, 3, -1, 0, 0, 0;
    0, 0, 0, 0, 0, -1, 3, -1, 0, 0;
    0, 0, 0, 0, 0, 0, -1, 3, -1, 0;
    0, 0, 0, 0, 0, 0, 0, -1, 3, -1;
    0, 0, 0, 0, 0, 0, 0, 0, -1, 3;
    ];

% Vectorul b pentru n = 10
b = [2.5, 
    1.5, 
    1.5, 
    1.5, 
    1.5, 
    1.0, 
    1.0, 
    1.5, 
    1.5, 
    2.5];

% Metoda LUP pentru n = 10
X = lup_decomposition(A, b);
disp(X);

% Metoda Cholesky pentru n = 10
R_Cholesky = cholesky_decomposition(A, b);
disp(R_Cholesky);

% Definim matricea A și vectorul b pentru n = 100
n = 100;
A = 3 * eye(n) - diag(ones(n-1, 1), -1) - diag(ones(n-1, 1), 1) + 0.5 * diag(ones(n-1, 1), n-1) + 0.5 * diag(ones(n-1, 1), -n+1);
b = [2.5 * ones(1, n-4), 1.5, 1.0, ones(1, n-4) * 1.5];

% Aplicăm descompunerile LUP și Cholesky pentru n = 100
X_lup = lup_decomposition(A, b);
X_cholesky = cholesky_decomposition(A, b);

fprintf('Soluția pentru n = 100 folosind LUP:\n');
disp(X_lup);

fprintf('Soluția pentru n = 100 folosind Cholesky:\n');
disp(X_cholesky);