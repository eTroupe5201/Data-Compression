section .data
    fmt db "%d", 10, -1

global main
extern printf

section .text

main:
        mov ecx, 999999
        xor edi, rdi        ; max
        xor ebx, rbx        ; max i

l1:
        xor     rsi, rsi
        mov     rax, rcx
l2:
        inc     rsi                 ; counter
        lea     rdx, [3*rax+1]      ; edx = 3*n+1
        shr     rax, 1              ; eax = n/2
        cmovc   rax, rdx            ; if CF eax = edx
        jnz     l2                  ; jmp if n<>1

        cmp     rdi, rsi
        cmovb   rdi, rsi
        cmovb   rbx, rcx

        dec     rcx
        jnz     l1
        
        mov rdi, fmt
        xor rax, rax
        call printf
        ret
