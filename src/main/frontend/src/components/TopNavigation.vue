<template>
    <nav class="flex flex-row items-center justify-between px-4 py-2 bg-[var(--md-sys-color-background)]">
        <div class="flex items-center gap-4">
            <slot name="header"></slot>            
        </div>
        
        <nav class="flex items-center justify-end">
            <slot></slot>
        </nav>
    </nav>
</template>

<script setup lang="tsx">
</script>

<style scoped></style>