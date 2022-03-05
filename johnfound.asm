section .data
    fmt db "%d", 10, 0

global main
extern printf

section .text

main:
        mov ecx, 999999
        xor edi, edi        ; max
        xor ebx, ebx        ; max i

l1:
        xor     esi, esi
        mov     eax, ecx
l2:
        inc     esi                 ; counter
        lea     edx, [3*eax+1]      ; edx = 3*n+1
        shr     eax, 1              ; eax = n/2
        cmovc   eax, edx            ; if CF eax = edx
        jnz     l2                  ; jmp if n<>1

        cmp     edi, esi
        cmovb   edi, esi
        cmovb   ebx, ecx

        dec     ecx
        jnz     l1
        
        mov rdi, fmt
        xor rax, rax
        call printf
        ret
